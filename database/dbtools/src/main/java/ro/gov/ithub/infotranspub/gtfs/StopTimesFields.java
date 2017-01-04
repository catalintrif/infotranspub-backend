package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;
	
	
	
	



public class StopTimesFields implements GTFSFields {
    public static final String FIELD_TRIP_ID = "trip_id";
    public static final String FIELD_ARRIVAL_TIME = "arrival_time";
    public static final String FIELD_DEPARTURE_TIME = "departure_time";
    public static final String FIELD_STOP_ID = "stop_id";
    public static final String FIELD_STOP_SEQUENCE = "stop_sequence";
    public static final String FIELD_STOP_HEADSIGN = "stop_headsign";
    public static final String FIELD_PICKUP_TYPE = "pickup_type";
    public static final String FIELD_DROP_OFF_TYPE = "drop_off_type";
    public static final String FIELD_SHAPE_DIST_TRAVELED = "shape_dist_traveled";
    public static final String FIELD_TIMEPOINT = "timepoint";

    public StopTimesFields() {
    }
    
    public String[] getFieldList() {
        String [] fields =  
                {     
                    StopTimesFields.FIELD_TRIP_ID,
                    StopTimesFields.FIELD_ARRIVAL_TIME,
                    StopTimesFields.FIELD_DEPARTURE_TIME,
                    StopTimesFields.FIELD_STOP_ID,
                    StopTimesFields.FIELD_STOP_SEQUENCE,
                    StopTimesFields.FIELD_STOP_HEADSIGN,
                    StopTimesFields.FIELD_PICKUP_TYPE,
                    StopTimesFields.FIELD_DROP_OFF_TYPE,
                    StopTimesFields.FIELD_SHAPE_DIST_TRAVELED,
                    StopTimesFields.FIELD_TIMEPOINT
                };
        return fields;
    }
    
}


