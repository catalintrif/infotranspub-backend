package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;


public class StopsColumns extends  SQLColumns {

	private StopsEntity entity = null;

	public StopsColumns(GTFSFields fields, StopsEntity entity){
		this.fields = fields;
		this.entity = entity;
	}

	public void setEntity(StopsEntity entity){
		this.entity = entity;
	}

		 
}


