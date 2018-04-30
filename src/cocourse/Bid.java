package cocourse;

public class Bid {

	private String user;
	private double amount;
	private long time;

	public Bid( String user , double amount ) {
		this.user = user;
		this.amount = amount;
		this.time = System.currentTimeMillis( );
	}

	public String getUser( ) {
		return user;
	}

	public void setUser( String user ) {
		this.user = user;
	}

	public double getAmount( ) {
		return amount;
	}

	public void setAmount( double amount ) {
		this.amount = amount;
	}

	public long getTime( ) {
		return time;
	}

	@Override
	public String toString( ) {
		return "Bid recieved at " + time + " from " + user + " for " + amount;
	}
}
