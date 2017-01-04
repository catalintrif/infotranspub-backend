package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;


public class CalendarDatesColumns extends  SQLColumns {

	private CalendarDatesEntity entity = null;

	public CalendarDatesColumns(GTFSFields fields, CalendarDatesEntity entity){
		this.fields = fields;
		this.entity = entity;
	}

	public void setEntity(CalendarDatesEntity entity){
		this.entity = entity;
	}

		 
}


