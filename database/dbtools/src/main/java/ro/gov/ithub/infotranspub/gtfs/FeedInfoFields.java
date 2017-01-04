package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;


public class FeedInfoFields implements GTFSFields {
    public static final String FIELD_FEED_PUBLISHER_NAME  = "feed_publisher_name";
    public static final String FIELD_FEED_PUBLISHER_URL  = "feed_publisher_url";
    public static final String FIELD_FEED_LANG  = "feed_lang";
    public static final String FIELD_FEED_START_DATE  = "feed_start_date";
    public static final String FIELD_FEED_END_DATE  = "feed_end_date";
    public static final String FIELD_FEED_VERSION = "feed_version";

    public FeedInfoFields() {
    }
    
    public String[] getFieldList() {
        String [] fields =  
                {     
                    FeedInfoFields.FIELD_FEED_PUBLISHER_NAME,
                    FeedInfoFields.FIELD_FEED_PUBLISHER_URL,
                    FeedInfoFields.FIELD_FEED_LANG,
                    FeedInfoFields.FIELD_FEED_START_DATE,
                    FeedInfoFields.FIELD_FEED_END_DATE,
                    FeedInfoFields.FIELD_FEED_VERSION
                };
        return fields;
    }
    
}


