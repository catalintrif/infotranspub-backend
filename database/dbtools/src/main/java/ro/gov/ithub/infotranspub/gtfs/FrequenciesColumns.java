package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;


public class FrequenciesColumns extends  SQLColumns {

	private FrequenciesEntity entity = null;

	public FrequenciesColumns(GTFSFields fields, FrequenciesEntity entity){
		this.fields = fields;
		this.entity = entity;
	}

	public void setEntity(FrequenciesEntity entity){
		this.entity = entity;
	}

		 
}


