package cocourse;

import java.util.ArrayList;

public class Auction {
	private String name;
	private String desc;
	private Bid bidHigh;
	private boolean running;
	private long duration;

	private long startTime;
	private long endTime;

	private ArrayList <Bid> bidHistory;

	public Auction( String name , String desc , Bid bid , long duration ) {
		this.name = name;
		this.desc = desc;
		this.bidHigh = bid;
		this.duration = duration;
		bidHistory = new ArrayList <>( );
		this.running = false;
	}

	public Auction( String name , String desc , Bid bid , long duration , boolean isrunning ) {
		this.name = name;
		this.desc = desc;
		this.bidHigh = bid;
		this.duration = duration;
		this.running = isrunning;
	}

	public boolean bid( Bid b ) {
		if ( b.getAmount( ) <= this.bidHigh.getAmount( ) ) return false;

		this.bidHistory.add( this.bidHigh );
		this.bidHigh = b;
		return true;
	}

	public String getName( ) {
		return name;
	}

	public String getDesc( ) {
		return desc;
	}

	public Bid getBidHigh( ) {
		return bidHigh;
	}

	public long getStartTime( ) {
		return startTime;
	}

	public long getEndTime( ) {
		return endTime;
	}

	public ArrayList <Bid> getBidHistory( ) {
		return bidHistory;
	}

	public boolean isRunning( ) {
		return this.getEndTime( ) > System.currentTimeMillis( );
	}

	public long timeLeft( ) {
		return this.endTime - System.currentTimeMillis( );
	}

	public void start( ) {
		this.startTime = System.currentTimeMillis( );
		this.endTime = this.startTime + (this.duration * 1000);
		this.running = !running;
	}

	public static Auction parseAuction( Packet p ) {

		String[] t = p.getContents( ).split( ";" );
		return new Auction(
				t[0] ,
				t[1] ,
				Bid.parseBid( t[2] ) ,
				Long.parseLong( t[3] ) ,
				Boolean.parseBoolean( t[4] ) );

	}

	public Packet toPacket( ) {
		return new Packet( "auction" , this.toString( ) );
	}

	public String toString( ) {
		return this.getName( ) + ";" +
				this.getDesc( ) + ";" +
				this.getBidHigh( ) + ";" +
				this.getEndTime( ) + ";" +
				this.isRunning( );
	}
}
//}
