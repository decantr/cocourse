package cocourse;

import java.util.ArrayList;

public class Auction {
	private String user;
	private String desc;
	private Bid bidHigh;
	private boolean running = false;
	private boolean ended = false;
	private long duration;

	private long startTime;
	private long endTime;

	private ArrayList <Bid> bidHistory;

	public Auction( String user , String desc , Bid bid , long duration ) {
		this.user = user;
		this.desc = desc;
		this.bidHigh = bid;
		this.duration = duration;
		this.bidHistory = new ArrayList <>( );
	}

	public Auction( String user , String desc , Bid bid , long duration , boolean isrunning ) {
		this.user = user;
		this.desc = desc;
		this.bidHigh = bid;
		this.bidHistory = new ArrayList <>( );
		this.duration = duration;
		this.running = isrunning;
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

	public synchronized boolean bid( Bid b ) {
		if ( !isRunning( ) ) return false;
		if ( b.getAmount( ) <= this.bidHigh.getAmount( ) ) return false;

		this.bidHistory.add( this.bidHigh );
		this.bidHigh = b;
		return true;
	}

	public String getUser( ) {
		return user;
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
		return running;
	}

	public long timeLeft( ) {
		return this.endTime - System.currentTimeMillis( );
	}

	public void start( ) {
		this.startTime = System.currentTimeMillis( );
		this.endTime = this.startTime + (this.duration * 1000);
		this.running = true;
	}

	public void stop( ) {
		this.running = false;
		this.setEnded( );
	}

	public Packet toPacket( ) {
		return new Packet( "auc" , this.toString( ) );
	}

	public String toString( ) {
		System.out.println( this.getEndTime() );
		return this.getUser( ) + ";" +
				this.getDesc( ) + ";" +
				this.getBidHigh( ) + ";" +
				this.getEndTime( ) + ";" +
				this.isRunning( );
	}

	public void setEnded( ) {
		this.ended = true;
	}

	public boolean getEnded( ) {
		return this.ended;
	}
}
//}
