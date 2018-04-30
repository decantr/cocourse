package cocourse;

public class Auction {
	private String name;
	private String desc;
	private double bidHigh;
	private boolean running;
	private long duration;

	private long startTime;
	private long endTime;

	private double[] bidHistory;

	public Auction( String name , String desc , double bidHigh , long duration ) {
		this.name = name;
		this.desc = desc;
		this.bidHigh = bidHigh;
		this.duration = duration;
		bidHistory = new double[400];
		this.running = false;
	}

	public boolean bid( double bid ) {
		if ( bid <= this.bidHigh ) return false;

		bidHistory[bidHistory.length - 1] = this.bidHigh;
		bidHigh = bid;
		return true;
	}

	public String getName( ) {
		return name;
	}

	public String getDesc( ) {
		return desc;
	}

	public double getBidHigh( ) {
		return bidHigh;
	}

	public long getStartTime( ) {
		return startTime;
	}

	public long getEndTime( ) {
		return endTime;
	}

	public double[] getBidHistory( ) {
		return bidHistory;
	}

	public boolean isRunning( ) {
		return this.getEndTime() > System.currentTimeMillis( );
	}

	public long timeLeft( ) {
		return this.endTime - System.currentTimeMillis( );
	}

	public void start( ) {
		this.startTime = System.currentTimeMillis( );
		this.endTime = this.startTime + ( this.duration * 1000 );
		this.running = !running;
	}
}
//}
