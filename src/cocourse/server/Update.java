package cocourse.server;


import cocourse.Packet;

public class Update extends Thread {

	private Server s;

	public Update( Server s ) {
		this.s = s;
	}

	@Override
	public void run(){
		while (true) {
			try {
				Thread.sleep( 1000L );
				Thread.yield( );
			} catch ( InterruptedException e ) {
				e.printStackTrace( );
			}

			Packet p;

			if ( s.getAuction( ) != null ) p = s.getAuction( ).toPacket( );
			else p = new Packet( "auction" , "none" );

			if ( s.getCons( ).size( ) > 0 )
				s.getCons( ).get( s.getCons( ).size( ) - 1 ).sendPacket( p );
			s.log( p );
		}
	}
}
