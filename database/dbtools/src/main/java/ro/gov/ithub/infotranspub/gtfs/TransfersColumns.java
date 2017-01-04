package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;


public class TransfersColumns extends  SQLColumns {

	private TransfersEntity entity = null;

	public TransfersColumns(GTFSFields fields, TransfersEntity entity){
		this.fields = fields;
		this.entity = entity;
	}

	public void setEntity(TransfersEntity entity){
		this.entity = entity;
	}

		 
}


