package cocourse;

public class Auction {
	private String name;
	private String desc;
	private double bidHigh;

	private long startTime;
	private long endTime;

	private double[] bidHistory;

	public Auction( String name , String desc , double bidHigh , long endTime ) {
		this.name = name;
		this.desc = desc;
		this.bidHigh = bidHigh;
		this.startTime = System.currentTimeMillis( );
		this.endTime = endTime;
		bidHistory = new double[400];
	}

	public boolean bid( double bid ) {
		if ( bid <= this.bidHigh ) return false;

		bidHistory[bidHistory.length - 1] = this.bidHigh;
		bidHigh = bid;
		return true;
	}

}
//}
