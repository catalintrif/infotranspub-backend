package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;




public class FareRulesFields implements GTFSFields {
    public static final String FIELD_FARE_ID = "fare_id";
    public static final String FIELD_ROUTE_ID = "route_id";
    public static final String FIELD_ORIGIN_ID = "origin_id";
    public static final String FIELD_DESTINATION_ID = "destination_id";
    public static final String FIELD_CONTAINS_ID = "contains_id";

    public FareRulesFields() {
    }
    
    public String[] getFieldList() {
        String [] fields =  
                {     
                    FareRulesFields.FIELD_FARE_ID, 
                    FareRulesFields.FIELD_ROUTE_ID, 
                    FareRulesFields.FIELD_ORIGIN_ID, 
                    FareRulesFields.FIELD_DESTINATION_ID, 
                    FareRulesFields.FIELD_CONTAINS_ID
                };
        return fields;
    }
    
}


