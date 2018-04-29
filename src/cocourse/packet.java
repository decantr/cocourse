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

	public String getType( ) {
		return this.type;
	}

	public String getTime( ) {
		return this.time;
	}

	public String getContents( ) {
		return contents;
	}

	@Override
	public String toString( ) {
		return time + ": " + contents;
	}
}
