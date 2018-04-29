package cocourse.server;

import cocourse.packet;
import cocourse.server.serverback;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class request implements Runnable {

	private Socket i;
	private serverback s;
	private BufferedReader r;
	private PrintWriter o;

	request( serverback s , Socket i ) {
		this.s = s;
		this.i = i;
	}

	public void close( ) {
		System.out.println( "shutting down" );
		try {
			i.close( );
		} catch ( Exception ignored ) {
		}
	}

	@Override
	public void run( ) {
		try {

//			set the reader and output streams
			r = new BufferedReader( new InputStreamReader( i.getInputStream( ) ) );
			o = new PrintWriter( i.getOutputStream( ) , true );

//			notify the user they connected
			o.println( new packet(
					"data" ,
					"connected " +
							i.getInetAddress( ).getHostName( ) +
							" (" + i.getInetAddress( ).getHostAddress( ) +
							") on " + i.getLocalPort( )
			) );

			while ( true ) {
				String t = r.readLine( );

				if ( t == null || t.equals( "exit" ) ) break;

				if ( t.equals( "b" ) )
				o.println( new packet( "command" , "test" ).send() );
			}

		} catch ( Exception e ) {
			e.printStackTrace();
		} finally {
			s.close( this );
		}
	}
}
