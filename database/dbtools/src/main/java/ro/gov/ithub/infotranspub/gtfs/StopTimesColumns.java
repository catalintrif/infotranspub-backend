package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;


public class StopTimesColumns extends  SQLColumns {

	private StopTimesEntity entity = null;

	public StopTimesColumns(GTFSFields fields, StopTimesEntity entity){
		this.fields = fields;
		this.entity = entity;
	}

	public void setEntity(StopTimesEntity entity){
		this.entity = entity;
	}

		 
}


