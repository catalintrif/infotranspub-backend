package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;


public class CalendarColumns extends  SQLColumns {

	private CalendarEntity entity = null;

	public CalendarColumns(GTFSFields fields, CalendarEntity entity){
		this.fields = fields;
		this.entity = entity;
	}

	public void setEntity(CalendarEntity entity){
		this.entity = entity;
	}

		 
}


