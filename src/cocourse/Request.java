package cocourse;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

class Request implements Runnable {

	private Socket i;

	Request ( Socket i ) {
		this.i = i;
	}


	@Override
	public void run() {
		try {

			BufferedReader r = new BufferedReader( new InputStreamReader( i.getInputStream() ) );

			PrintWriter o = new PrintWriter( i.getOutputStream() );

			boolean c = true;

			while ( c ) {

				String t = r.readLine();

				if ( t != null ) System.out.println( t );

			}

		} catch ( Exception e ) {
			System.out.println( e );
		} finally {
			try { i.close(); } catch ( Exception ignored ) {}
		}
	}
}
