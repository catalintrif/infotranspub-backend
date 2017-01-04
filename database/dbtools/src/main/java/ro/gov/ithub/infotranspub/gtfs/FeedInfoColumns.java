package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;


public class FeedInfoColumns extends  SQLColumns {

	private FeedInfoEntity entity = null;

	public FeedInfoColumns(GTFSFields fields, FeedInfoEntity entity){
		this.fields = fields;
		this.entity = entity;
	}

	public void setEntity(FeedInfoEntity entity){
		this.entity = entity;
	}

		 
}


