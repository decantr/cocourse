package cocourse.client;

import cocourse.Address;
import cocourse.Auction;
import cocourse.Bid;
import cocourse.Packet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread {

	private String user;
	private Address ip;
	private Socket socket;
	private BufferedReader r;
	private PrintWriter o;
	private Auction auction;

	public Client( String user , Address ip ) {
		this.user = user;
		this.ip = ip;
	}

	public Client( String hostname , int port ) {
		this.ip = new Address( hostname.equals( "" ) ? "127.0.0.1" : hostname , "" , port );
	}

	public Auction getAuction( ) {
		return this.auction;
	}

	public boolean connected () {
		if ( this.socket == null ) return false;
		return this.socket.isConnected();
	}

	public void makeBid( double amount ) {
		amount = (double) Math.round(amount * 100) / 100;
		Bid b = new Bid( user , amount );
		if ( b.getAmount( ) > auction.getBidHigh( ).getAmount( ) )
			sendPacket( new Packet( "bid" , b.toString( ) ) );
	}

	public String getUser( ) {
		return user;
	}

	public void sendPacket( Packet packet ) {
		o.println( packet );
	}

	public void run( ) {
		try {
			socket = new Socket( this.ip.getIp( ) , this.ip.getPort( ) );

			r = new BufferedReader( new InputStreamReader( socket.getInputStream( ) ) );
			o = new PrintWriter( socket.getOutputStream( ) , true );

			sendPacket( new Packet( "log" , ip.toString( ) ) );
			sendPacket( new Packet( "get", "" ) );

			while ( true ) {

				String t = r.readLine( );

				if ( t == null || t.length( ) == 0 ) continue;

				Packet p = Packet.parsePacket( t );

				switch (p.getType( )) {
					case "auc":
						auction = Auction.parseAuction( p );
						break;
					case "nauc":
						auction = null;
						break;
					case "end":
						auction.setEnded();
						break;
					default:
						System.out.println( p.toString( ) );
						break;
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
		return this.socket != null;
	}
}
