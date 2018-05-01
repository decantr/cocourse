package cocourse.server;

import cocourse.Address;
import cocourse.Bid;
import cocourse.Packet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Connection implements Runnable {

	private Socket i;
	private Server s;
	private BufferedReader r;
	private PrintWriter o;
	private boolean run;

	private Address ip;

	Connection( Server s , Socket i ) {
		this.s = s;
		this.i = i;
		this.ip = new Address(
				i.getInetAddress( ).getHostName( ) ,
				i.getInetAddress( ).getHostAddress( ) ,
				i.getLocalPort( ) );
		this.run = true;
	}

	public void sendPacket( Packet packet ) {
		this.o.println( packet );
	}

	public void shutdown(  ) {
		this.run = false;
	}

	@Override
	public void run( ) {
		try {

//			set the reader and output streams
			r = new BufferedReader( new InputStreamReader( i.getInputStream( ) ) );
			o = new PrintWriter( i.getOutputStream( ) , true );

//			notify the user they connected
			sendPacket( new Packet(
					"log" , "connected " + s.getIp( ).toString( ) ) );



//			get auction info
			sendPacket( s.getAuctionPacket( ) );

			while ( run ) {

				String t = r.readLine( );

				if ( t == null || t.length( ) == 0 ) continue;

				Packet p = Packet.parsePacket( t );

				switch (p.getType( )) {
					case "get":
						sendPacket( s.getAuctionPacket( ) );
						break;
					case "bid":
						s.bid( Bid.parseBid( p.getContents( ) ) );
						s.log( p );
						break;
					case "log":
						s.log( p );
						break;
					default:
						s.log( p );
						break;
				}

			}
		} catch ( Exception e ) {
			e.printStackTrace( );
		} finally {
			s.log( "log" , "Disconnected" + ip.toString( ) );
			try {
				r.close( );
				o.close( );
				i.close( );
			} catch ( Exception ignored ) {
			}
		}
	}

	public Address getIp( ) {
		return ip;
	}


}
