package cocourse.server;

import cocourse.Address;
import cocourse.Auction;
import cocourse.Bid;
import cocourse.Packet;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Server extends Thread {

	private final static short min = 1024;
	private final static int max = 49151;
	private static final byte threads = 4;
	private static final byte q = 8;

	private ArrayList <Connection> cons;
	private ArrayList <Packet> log;

	private ServerSocket sock;
	private ThreadPoolExecutor pool;
	private Address ip;
	private Auction auction;
	private boolean running;

	public Server( int port ) {
//		double check if the port is valid
		if ( !portFree( port ) ) return;

		this.cons = new ArrayList <>( );

		this.pool = new ThreadPoolExecutor(
				threads , threads , 1 , TimeUnit.MINUTES , new LinkedBlockingDeque <>( q )
		);
		this.pool.setRejectedExecutionHandler( new ThreadPoolExecutor.DiscardPolicy( ) );

		try {
			this.ip = new Address( InetAddress.getLocalHost( ).getHostAddress( ) , InetAddress.getLocalHost( )
					.getHostName( ) , port );
		} catch ( Exception e ) {
			this.ip = new Address( "127.0.0.1" , "localhost" , port );
		}

		this.log = new ArrayList <>( );
		this.log( "log" , "server created" );
		this.log("log", ip.toString());
	}

	public Auction getAuction( ) {
		return auction;
	}

	public Packet getAuctionPacket( ) {
		return auction != null ? auction.toPacket( ) : new Packet( "nuc", "" );
	}

	public void setAuction( Auction auction ) {
		this.auction = auction;
		this.log("log" , "auction created");
		this.log("log", this.auction.toString());
		notifyClients();
	}

	public void startAuction( ) {
		this.auction.start();
		this.notifyClients();
		this.log("log", "auction started");
	}

	public ArrayList <Connection> getCons( ) {
		return cons;
	}

	private void notifyClients( ) {
		for ( Connection c : this.cons ) c.sendPacket( getAuctionPacket( ) );
	}

	private void notifyClients( Packet p ) {
		for ( Connection c : this.cons ) c.sendPacket( p );
	}

	public boolean isRunning( ) {
		return running;
	}

	public Address getIp( ) {
		return this.ip;
	}

	//	return the log
	public ArrayList <Packet> getLog( ) {
		return this.log;
	}

	//	add to the log
	public void log( Packet a ) {
		this.log.add( a );
	}

	public void log( String level , String contents ) {
		this.log(new Packet( level , contents ) );
	}

	@Override
	public void run( ) {

		try {
//      create new socket on the port passed in
			this.sock = new ServerSocket( ip.getPort() );
			this.log( "log" , "server listening on " + this.sock.getLocalPort( ) );
			this.ip.setPort( this.sock.getLocalPort( ) );

			running = true;

//			wait for requests and submit them to the pool
			while ( true ) {

				Connection c = new Connection( this , this.sock.accept( ) );
				cons.add( c );
				pool.submit( c );

				this.log( "log" , "Connected " + c.getIp( ).toString( ) );
			}
		} catch ( Exception e ) {
			e.printStackTrace();
			System.exit( 0 );
		} finally {
//			try shutdown the pool and close the socket
			try {
				pool.shutdown( );
				sock.close( );
			} catch ( Exception ignored ) {
			}
		}
	}

	//	method to check port availability
	public static boolean portFree( int p ) {

		ServerSocket s = null;

		if ( p == 0 || ( p < max && p > min )) {
			try {

				s = new ServerSocket( p );
				return true;

			} catch ( Exception ignored ) {
			} finally {
				if ( s != null )
					try {
						s.close( );
					} catch ( Exception ignored ) {
					}
			}
		}
		return false;
	}

	public void bid( Bid bid ) {
		auction.bid( bid );
		notifyClients();
	}

	public void stopAuction( ) {
		auction.stop();
		notifyClients();
		notifyClients( new Packet( "end", "" ));
	}
}
