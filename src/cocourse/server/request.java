package cocourse.server;

import cocourse.packet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class request implements Runnable {

	private Socket i;
	private serverback s;
	private BufferedReader r;
	private PrintWriter o;

	private ip ip;

	request( serverback s , Socket i ) {
		this.s = s;
		this.i = i;
		this.ip = new ip (
				i.getInetAddress( ).getHostName( ) ,
				i.getInetAddress( ).getHostAddress( ) ,
				i.getLocalPort( ));
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
			packet p = new packet(
					"log" ,
					"connected " + s.getIp() + "(" + ") on " + s.getIp().getPort() );

			o.println( p );

			while ( true ) {
				String t = r.readLine( );

				if ( t == null || t.equals( "exit" ) ) break;

				if ( t.equals( "b" ) )
					o.println( new packet( "command" , "test" ).send( ) );
			}

		} catch ( Exception e ) {
			e.printStackTrace( );
		} finally {
			s.close( this );
		}
	}

	public ip getIp( ) {
		return ip;
	}


}
