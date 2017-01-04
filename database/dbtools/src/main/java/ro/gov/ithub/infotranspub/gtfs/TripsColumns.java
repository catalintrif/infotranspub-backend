package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;


public class TripsColumns extends  SQLColumns {

	private TripsEntity entity = null;

	public TripsColumns(GTFSFields fields, TripsEntity entity){
		this.fields = fields;
		this.entity = entity;
	}

	public void setEntity(TripsEntity entity){
		this.entity = entity;
	}

		 
}


