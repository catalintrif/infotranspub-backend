package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;


public class AgencyColumns extends  SQLColumns {

	private AgencyEntity entity = null;

	public AgencyColumns(GTFSFields fields, AgencyEntity entity){
		this.fields = fields;
		this.entity = entity;
	}

	public void setEntity(AgencyEntity entity){
		this.entity = entity;
	}

		 
}


