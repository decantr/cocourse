package cocourse.client;

import cocourse.Address;
import cocourse.Auction;
import cocourse.Packet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread {

	private Address ip;
	private Socket socket;
	private BufferedReader r;
	private PrintWriter o;
	private Auction auction;
	private boolean running = true;


	public Client( Address ip ) {
		this.ip = ip;
	}

	public Client( String hostname , int port ) {
		this.ip = new Address( hostname.equals( "" ) ? "127.0.0.1" : hostname , "" , port );
	}

	public void sendMessage( Packet p ) {
		o.println( p );
	}

	public Auction getAuction( ) {
		return this.auction;
	}

	public void run( ) {
		try {
			socket = new Socket( this.ip.getIp( ) , this.ip.getPort( ) );
			r = new BufferedReader( new InputStreamReader( socket.getInputStream( ) ) );
			o = new PrintWriter( socket.getOutputStream( ) , true );
			o.println( new Packet( "handshake" , ip.toString( ) ) );

			while ( running ) {
				String t = r.readLine( );

				if ( t != null && t.length( ) > 0 ) continue;

				Packet p = null;

				try {
					p = Packet.parsePacket( t );
				} catch ( Exception ignored ) {
					System.out.println( t );
				}

				if ( p != null ) switch (p.getType( )) {
					case "auction":
						this.auction = Auction.parseAuction( Packet.parsePacket( t ) );
						break;
					default:
						System.out.println( p.toString() );
				}
			}
		} catch ( Exception e ) {
			e.printStackTrace( );
		} finally {
			try {
				socket.close( );
			} catch ( Exception ignored ) {
			}

		}
	}

	public boolean isRunning( ) {
		if ( this.socket == null ) return false;
		return running;
	}
}
