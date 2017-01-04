package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;

public class FeedInfo {

	public static final String FILE_NAME = "feed_info.txt";

	private FeedInfoFields fields = null;
	private FeedInfoColumns columns = null;
	private FeedInfoEntity entity = null;
	
	public FeedInfo( FeedInfoFields fields,  FeedInfoEntity entity, FeedInfoColumns columns){
		this.fields = fields;
		this.entity = entity;
		this.columns = columns;
	}

	public FeedInfo(){
		this.fields = new FeedInfoFields();
		this.entity = new FeedInfoEntity();
		this.columns = new FeedInfoColumns(this.fields, this.entity);
	}


}


