package cocourse;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Packet {

	private String type;
	private String contents;
	private String time;

	public Packet( String t , String c ) {
		this.type = t;
		this.contents = c;
		this.time = new SimpleDateFormat( "yyyy.MM.dd.HH.mm.ss" ).format( new Date( ) );
	}

	public Packet( String l , String c , String t ) {
		this.type = l;
		this.contents = c;
		this.time = t;
	}

	public static Packet parsePacket( String m ) {
		System.out.println( m );
		String[] r = m.split( ":" , 3 );

		return new Packet( r[0] , r[2] , r[1] );
	}

	public String send( ) {
		return this.getType( ) + ":" + this.getTime( ) + ":" + this.getContents( );
	}

	public String getType( ) {
		return this.type;
	}

	public String getTime( ) {
		return this.time;
	}

	public String getContents( ) {
		return this.contents;
	}

	@Override
	public String toString( ) {
		return type + ":" + time + ":" + contents;
	}
}
