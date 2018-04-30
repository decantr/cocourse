package cocourse.server;

import cocourse.Address;
import cocourse.Packet;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Connection implements Runnable {

	private Socket i;
	private Server s;
	private BufferedReader r;
	private PrintWriter o;
	private boolean run = true;

	private Address ip;

	Connection( Server s , Socket i ) {
		this.s = s;
		this.i = i;
		this.ip = new Address(
				i.getInetAddress( ).getHostName( ) ,
				i.getInetAddress( ).getHostAddress( ) ,
				i.getLocalPort( ));
	}

	public void sendPacket( Packet packet) {
		this.o.println( packet );
	}

	public void close() {
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
			o.println(new Packet(
					"log" , "connected " + s.getIp().toString() ));

			while ( run ) {
				String t = r.readLine( );

				if ( t == null || t.length() == 0 ) continue;

				System.out.println( t );

				Packet p = Packet.parsePacket(t);

				if ( t.equals( "b" ) )
					o.println( new Packet( "command" , "test" ).send( ) );
			}
			close();

		} catch ( Exception ignored ) {}
	}

	public Address getIp( ) {
		return ip;
	}


}
