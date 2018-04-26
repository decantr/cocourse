package cocourse;

import java.net.ServerSocket;

public class ServerBack {

	private final static short min = 1024;
	private final static int max = 49151;
	private ServerSocket s;

	public boolean create( int port ) {

		if ( port > max || port < min ) return false;

		try {

			this.s = new ServerSocket( port );

			while (true) {

				new Request ( s.accept() ).run ( );

			}

		} catch ( Exception e ) {

			System.out.println( " port already in use" );
			return false;

		} finally {
			try {	s.close(); } catch ( Exception ignored ) { }
		}
	}
}
