package cocourse.client;

import cocourse.ip;
import cocourse.packet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class clientback extends Thread {

	private ip ip;
	private Socket socket;
	private BufferedReader r;
	private PrintWriter o;

	public clientback( String hostname , int port ) {
		this.ip = new ip( hostname.equals( "" ) ? "127.0.0.1" : hostname , "" , port );
	}

	public void run( ) {
		try {
			socket = new Socket( this.ip.getIp( ) , this.ip.getPort( ) );
			r = new BufferedReader( new InputStreamReader( socket.getInputStream( ) ) );
			o = new PrintWriter( socket.getOutputStream( ) , true );
			o.println( new packet( "handshake" , "hello" ) );


			while ( true ) {
				String t = r.readLine( );

				if ( t == null || t.equals( "exit" ) ) break;

				if ( t.equals( "b" ) )
					o.println( new packet( "command" , "test" ).send( ) );
			}

		} catch ( Exception ignored ) {
		} finally {

			try {
				socket.close( );
			} catch ( Exception ignored ) {
			}

		}
	}


}
