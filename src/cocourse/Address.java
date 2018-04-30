package cocourse;

public class Address {
	private String ip;
	private String hostname;
	private int port;

	public Address( String hostname , int port ) {
		this.hostname = hostname;
		this.port = port;
	}

	public Address( String ip , String hostname , int port ) {
		this.ip = ip;
		this.hostname = hostname;
		this.port = port;
	}

	public String getIp( ) {
		return this.ip;
	}

	public String getHostname( ) {
		return this.hostname;
	}

	public int getPort( ) {
		return this.port;
	}

	public void setPort( int port ) {
		this.port = port;
	}

	@Override
	public String toString( ) {
		return this.ip + " (" + this.hostname + ") on " + this.port;
	}
}
