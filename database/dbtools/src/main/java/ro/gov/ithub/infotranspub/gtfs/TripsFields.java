package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;


public class TripsFields implements GTFSFields {
    public static final String FIELD_ROUTE_ID = "route_id";
    public static final String FIELD_SERVICE_ID = "service_id";
    public static final String FIELD_TRIP_ID = "trip_id";
    public static final String FIELD_TRIP_HEADSIGN = "trip_headsign";
    public static final String FIELD_TRIP_SHORT_NAME = "trip_short_name";
    public static final String FIELD_DIRECTION_ID = "direction_id";
    public static final String FIELD_BLOCK_ID = "block_id";
    public static final String FIELD_SHAPE_ID = "shape_id";
    public static final String FIELD_WHEELCHAIR_ACCESSIBLE = "wheelchair_accessible";
    public static final String FIELD_BIKES_ALLOWED = "bikes_allowed";

    public TripsFields() {
    }
    
    public String[] getFieldList() {
        String [] fields =  
                {     
                    TripsFields.FIELD_ROUTE_ID, 
                    TripsFields.FIELD_SERVICE_ID, 
                    TripsFields.FIELD_TRIP_ID, 
                    TripsFields.FIELD_TRIP_HEADSIGN, 
                    TripsFields.FIELD_TRIP_SHORT_NAME, 
                    TripsFields.FIELD_DIRECTION_ID, 
                    TripsFields.FIELD_BLOCK_ID, 
                    TripsFields.FIELD_SHAPE_ID, 
                    TripsFields.FIELD_WHEELCHAIR_ACCESSIBLE, 
                    TripsFields.FIELD_BIKES_ALLOWED
                };
        return fields;
    }
    
}


