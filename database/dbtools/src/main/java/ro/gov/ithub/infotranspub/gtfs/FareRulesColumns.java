package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;


public class FareRulesColumns extends  SQLColumns {

	private FareRulesEntity entity = null;

	public FareRulesColumns(GTFSFields fields, FareRulesEntity entity){
		this.fields = fields;
		this.entity = entity;
	}

	public void setEntity(FareRulesEntity entity){
		this.entity = entity;
	}

		 
}


