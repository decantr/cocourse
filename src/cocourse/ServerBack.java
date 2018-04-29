package cocourse;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ServerBack extends Thread {

	private final static short min = 1024;
	private final static int max = 49151;
	private static final byte threads = 4;
	private static final byte q = 8;

	private boolean go;
	private ArrayList <Request> cons;

	private ServerSocket sock;
	private ThreadPoolExecutor pool;
	private int port;


	public ServerBack ( int port ) {
//		double check if the port is valid
		if ( port > max || port < min ) return;
		else this.port = port;

		this.cons = new ArrayList <Request>( );
		this.go = true;

		this.pool = new ThreadPoolExecutor(
				threads , threads , 1 , TimeUnit.MINUTES , new LinkedBlockingDeque <>( q )
		);
		this.pool.setRejectedExecutionHandler( new ThreadPoolExecutor.DiscardPolicy( ) );
	}


	//	method to stop the server
	public void pause () {
		this.go = false;
	}


	//	method to resume
	public void go () {
		this.go = true;
	}

//	method to close a Request
	public void close (Request r) {
		r.close();
		this.cons.remove( r );
	}


	//	return false if any issues are found
	@Override
	public void run () {

		try {
//      create new socket on the port passed in
			this.sock = new ServerSocket( this.port );

//			wait for requests and submit them to the pool
			while ( go ) {
				cons.add( new Request( this , sock.accept( ) ) );
				pool.submit( cons.get( cons.size( ) - 1 ) );
			}

		} catch ( Exception e ) {

//			catch port in use
			e.printStackTrace( );

		} finally {

			try {
//				shutdown the pool and close the socket
				pool.shutdown( );
				sock.close( );
			} catch ( Exception ignored ) {
			}

		}

	}
}
