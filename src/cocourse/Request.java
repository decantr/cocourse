package cocourse;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class Request implements Runnable {

	private Socket i;
	private ServerBack s;
	private BufferedReader r;
	private PrintWriter o;

	Request ( ServerBack s , Socket i ) {
		this.s = s;
		this.i = i;
	}

	public void close () {
		System.out.println( "shutting down" );
		try {
			i.close( );
		} catch ( Exception ignored ) {
		}
	}

	@Override
	public void run () {
		try {

			r = new BufferedReader( new InputStreamReader( i.getInputStream( ) ) );

			o = new PrintWriter( i.getOutputStream( ) );

			o.println( "connected" + i.getInetAddress( ) );

			while ( true ) {

				String t = r.readLine( );

				if ( t == null || t.equals( "exit" ) ) break;

				if ( t.equals( "b" ) ) System.out.println( t );

			}

		} catch ( Exception e ) {
			System.out.println( e );
		} finally {
			s.close( this );
		}
	}
}
