package cocourse;

import java.text.SimpleDateFormat;
import java.util.Date;

public class packet {

	private String type;
	private String contents;
	private String time;

	public packet( String t , String c ) {
		this.type = t;
		this.contents = c;
		this.time = new SimpleDateFormat( "yyyy.MM.dd.HH.mm.ss" ).format( new Date( ) );
	}

	public packet ( String m ) {
		String[] r = m.split( ":" , 3 );

		this.type = r[0];
		this.contents = r[2];
		this.time = r[1];
	}

	public String send ( ) {
		return this.getType() + ":" + this.getTime() + ":" + this.getContents();
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
		return time + ": " + contents;
	}
}
