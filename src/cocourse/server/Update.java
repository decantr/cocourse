package cocourse.server;

public class Update extends Thread {

	private Server s;
	private boolean going;

	public Update( Server s ) {
		this.s = s;
	}

	@Override
	public void run( ) {
		going = true;
		while ( going ) {
			try {
				Thread.sleep( 100L );
			} catch ( InterruptedException e ) {
				e.printStackTrace( );
			}

			synchronized (s) {
				if ( System.currentTimeMillis( ) < s.getAuction( ).getEndTime( ) ) Thread.yield( );
				else {
					s.stopAuction( );
					going = false;
				}
			}
		}
	}
}
