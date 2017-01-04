package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;



public class FrequenciesFields implements GTFSFields {
    public static final String FIELD_TRIP_ID = "trip_id";
    public static final String FIELD_START_TIME = "start_time";
    public static final String FIELD_END_TIME = "end_time";
    public static final String FIELD_HEADWAY_SECS = "headway_secs";
    public static final String FIELD_EXACT_TIMES = "exact_times";

    public FrequenciesFields() {
    }
    
    public String[] getFieldList() {
        String [] fields =  
                {     
                    FrequenciesFields.FIELD_TRIP_ID, 
                    FrequenciesFields.FIELD_START_TIME, 
                    FrequenciesFields.FIELD_END_TIME, 
                    FrequenciesFields.FIELD_HEADWAY_SECS, 
                    FrequenciesFields.FIELD_EXACT_TIMES
                };
        return fields;
    }
    
}


