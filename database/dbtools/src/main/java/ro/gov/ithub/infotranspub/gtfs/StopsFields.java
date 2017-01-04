package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;


public class StopsFields implements GTFSFields {
	public static final String FIELD_STOP_ID = "stop_id";
	public static final String FIELD_STOP_CODE = "stop_code";
	public static final String FIELD_STOP_NAME = "stop_name";
	public static final String FIELD_STOP_DESC = "stop_desc";
	public static final String FIELD_STOP_LAT = "stop_lat";
	public static final String FIELD_STOP_LON = "stop_lon";
	public static final String FIELD_ZONE_ID = "zone_id";
	public static final String FIELD_STOP_URL = "stop_url";
	public static final String FIELD_LOCATION_TYPE = "location_type";
	public static final String FIELD_PARENT_STATION = "parent_station";
	public static final String FIELD_STOP_TIMEZONE = "stop_timezone";
	public static final String FIELD_WHEELCHAIR_BOARDING = "wheelchair_boarding";

	public StopsFields() {
	}
	
	public String[] getFieldList() {
		String [] fields =  { 	StopsFields.FIELD_STOP_ID, 
					StopsFields.FIELD_STOP_CODE, 
					StopsFields.FIELD_STOP_NAME, 
					StopsFields.FIELD_STOP_DESC, 
					StopsFields.FIELD_STOP_LAT, 
					StopsFields.FIELD_STOP_LON, 
					StopsFields.FIELD_ZONE_ID, 
					StopsFields.FIELD_STOP_URL, 
					StopsFields.FIELD_LOCATION_TYPE, 
					StopsFields.FIELD_PARENT_STATION, 
					StopsFields.FIELD_STOP_TIMEZONE, 
					StopsFields.FIELD_WHEELCHAIR_BOARDING};
		return fields;
	}
	
}


