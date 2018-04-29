package cocourse;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class serverback extends Thread {

	private final static short min = 1024;
	private final static int max = 49151;
	private static final byte threads = 4;
	private static final byte q = 8;

	private ArrayList <request> cons;

	private ServerSocket sock;
	private ThreadPoolExecutor pool;
	private int port;


	public serverback( int port ) {
//		double check if the port is valid
		if ( port > max || port < min ) return;
		else this.port = port;

		this.cons = new ArrayList <>( );

		this.pool = new ThreadPoolExecutor(
				threads , threads , 1 , TimeUnit.MINUTES , new LinkedBlockingDeque <>( q )
		);
		this.pool.setRejectedExecutionHandler( new ThreadPoolExecutor.DiscardPolicy( ) );
	}

	@Override
	public void run () {

		try {
//      create new socket on the port passed in
			this.sock = new ServerSocket( this.port );

//			wait for requests and submit them to the pool
			while ( true ) {
				cons.add( new request( this , sock.accept( ) ) );
				pool.submit( cons.get( cons.size( ) - 1 ) );
			}

		} catch ( Exception ignored ) {
		} finally {
			try {
//				shutdown the pool and close the socket
				pool.shutdown( );
				sock.close( );
			} catch ( Exception ignored ) { }
		}

	}

	//	method to close a request
	void close ( request r) {
		r.close();
		this.cons.remove( r );
	}
}
