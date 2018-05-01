package cocourse;

public class Bid {

	private String user;
	private double amount;
	private long time;

	public Bid( String user , double amount ) {
		this.user = user.equals( "" ) ? "default" : user;
		this.amount = amount;
		this.time = System.currentTimeMillis( );
	}

	public Bid( String user , double amount , long time ) {
		this.user = user.equals( "" ) ? "default" : user;
		this.amount = amount;
		this.time = time;
	}

	public static Bid parseBid( String s ) {
		String[] t = s.split( "&" );
		return new Bid( t[0] , Double.parseDouble( t[1] ) , Long.parseLong( t[2] ) );
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
		return user + "&" + amount + "&" + time;
	}
}
