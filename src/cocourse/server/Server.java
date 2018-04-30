package cocourse.server;

import cocourse.Address;
import cocourse.Packet;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
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


	public Server( int port ) {
//		double check if the port is valid
		if ( ! portFree( port ) ) return;

		this.cons = new ArrayList <>( );

		this.pool = new ThreadPoolExecutor(
				threads , threads , 1 , TimeUnit.MINUTES , new LinkedBlockingDeque <>( q )
		);
		this.pool.setRejectedExecutionHandler( new ThreadPoolExecutor.DiscardPolicy( ) );

		try {
			this.ip = new Address( InetAddress.getLocalHost().getHostAddress(), InetAddress.getLocalHost().getHostName(), port );
		} catch ( UnknownHostException e ) {
			this.ip = new Address( "127.0.0.1" , "localhost" , port );
		}

		this.log = new ArrayList <>(  );
		this.log( "log" , "server created" );
	}

	@Override
	public void run( ) {

		try {
//      create new socket on the port passed in
			this.sock = new ServerSocket( this.getIp().getPort() );
			this.ip.setPort( this.sock.getLocalPort() );
			this.log("log" , "server listening on " + this.sock.getLocalPort() );

//			wait for requests and submit them to the pool
			while ( true ) {

				cons.add( new Connection( this , this.sock.accept( ) ) );

				pool.submit( cons.get( cons.size( ) - 1 ) );

				this.log("log" , "new connection from " + cons.get( cons.size() - 1).getIp().toString() );

			}

		} catch ( Exception ignored ) {
		} finally {
			try {
//				shutdown the pool and close the socket
				this.log("log", "connection closed" );
				pool.shutdown( );
				sock.close( );
			} catch ( Exception ignored ) {
			}
		}

	}

	//	method to close a Connection
	public void close( Connection r ) {
		r.close( );
		this.cons.remove( r );
	}

	//	method to check port availability
	public boolean portFree ( int p ) {

		ServerSocket s = null;

		if ( p > max || p < min ) {
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

	public Address getIp () {
		return this.ip;
	}

//	return the log
	public ArrayList<Packet> getLog() {
		return this.log;
	}

//	add to the log
	public void log ( Packet a ) {
		this.log.add( a );
	}

	public void log ( String level, String contents ) {
		Packet p = new Packet( level , contents );
		this.log(p);
	}
}
