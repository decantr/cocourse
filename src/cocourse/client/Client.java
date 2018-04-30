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

	public Client( String hostname , int port ) {
		this.ip = new Address( hostname.equals( "" ) ? "127.0.0.1" : hostname , "" , port );
	}

	public Auction getAuction () {
		return this.auction;
	}

	public void run( ) {
		try {
			socket = new Socket( this.ip.getIp( ) , this.ip.getPort( ) );
			r = new BufferedReader( new InputStreamReader( socket.getInputStream( ) ) );
			o = new PrintWriter( socket.getOutputStream( ) , true );
			o.println( new Packet( "handshake" , "hello" ) );


			while ( true ) {
				String t = r.readLine( );

				if ( t == null || t.equals( "exit" ) ) break;

				Packet p = new Packet( t );

				if ( t.split( ":" , 1 ).equals( "auction" ))
					this.auction = Auction.parseAuction( p );

				if ( t.equals( "b" ) )
					o.println( new Packet( "command" , "test" ).send( ) );
			}

		} catch ( Exception ignored ) {
		} finally {

			try {
				socket.close( );
			} catch ( Exception ignored ) {
			}

		}
	}


}
