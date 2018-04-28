package cocourse;

import java.net.ServerSocket;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ServerBack {

	private final static short min = 1024;
	private final static int max = 49151;
	private static final int threads = 4;
	private static final int tasks = 4;
	private static final int q = 8;
	private boolean run = true;
	private ServerSocket s;
	private ThreadPoolExecutor pool;

//	return false if any issues are found
	public boolean create( int port ) {

//		check if the port if valid
		if ( port > max || port < min ) return false;

//		creaete a pool for the threads
		pool = new ThreadPoolExecutor(
				threads, threads, 1 , TimeUnit.MINUTES, new LinkedBlockingDeque <>( q )
		);

//		set the pool to discard extra threads after it's full
		pool.setRejectedExecutionHandler( new ThreadPoolExecutor.DiscardPolicy() );

		try {

//      create new socket on the port passed in
			this.s = new ServerSocket( port );

//			wait for requests and submit them to the pool
			while (run) pool.submit( new Request( s.accept() ) );

		} catch ( Exception e ) {

//			catch port in use
			System.out.println( "=port already in use" );
			return false;

		} finally {

			try {
//				shutdown the pool and close the socket
				pool.shutdown();
				s.close();
			} catch ( Exception ignored ) { }

		}
		return true;
	}
}
