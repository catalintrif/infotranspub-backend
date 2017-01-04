package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;

public class CalendarDates {

	public static final String FILE_NAME = "calendar_dates.txt";

	private CalendarDatesFields fields = null;
	private CalendarDatesColumns columns = null;
	private CalendarDatesEntity entity = null;
	
	public CalendarDates( CalendarDatesFields fields,  CalendarDatesEntity entity, CalendarDatesColumns columns){
		this.fields = fields;
		this.entity = entity;
		this.columns = columns;
	}

	public CalendarDates(){
		this.fields = new CalendarDatesFields();
		this.entity = new CalendarDatesEntity();
		this.columns = new CalendarDatesColumns(this.fields, this.entity);
	}


}


