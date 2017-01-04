package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;

public class Calendar {

	public static final String FILE_NAME = "calendar.txt";

	private CalendarFields fields = null;
	private CalendarColumns columns = null;
	private CalendarEntity entity = null;
	
	public Calendar( CalendarFields fields,  CalendarEntity entity, CalendarColumns columns){
		this.fields = fields;
		this.entity = entity;
		this.columns = columns;
	}

	public Calendar(){
		this.fields = new CalendarFields();
		this.entity = new CalendarEntity();
		this.columns = new CalendarColumns(this.fields, this.entity);
	}


}


