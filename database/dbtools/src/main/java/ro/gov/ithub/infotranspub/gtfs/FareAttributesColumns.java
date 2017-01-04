package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;


public class FareAttributesColumns extends  SQLColumns {

	private FareAttributesEntity entity = null;

	public FareAttributesColumns(GTFSFields fields, FareAttributesEntity entity){
		this.fields = fields;
		this.entity = entity;
	}

	public void setEntity(FareAttributesEntity entity){
		this.entity = entity;
	}

		 
}


