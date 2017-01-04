package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;


public class RoutesColumns extends  SQLColumns {

	private RoutesEntity entity = null;

	public RoutesColumns(GTFSFields fields, RoutesEntity entity){
		this.fields = fields;
		this.entity = entity;
	}

	public void setEntity(RoutesEntity entity){
		this.entity = entity;
	}

		 
}


