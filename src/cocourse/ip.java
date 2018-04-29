package cocourse;

public class ip {
	private String ip;
	private String hostname;
	private int port;

	public ip( String ip , String hostname , int port ) {
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

	public String ipv4 ( ) {
		return this.ip + ":" + this.port;
	}
	@Override
	public String toString( ) {
		return this.ip + " (" + this.hostname + ") on " + this.port;
	}
}
