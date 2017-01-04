package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;


public class RoutesFields implements GTFSFields {
    public static final String FIELD_ROUTE_ID= "route_id";
    public static final String FIELD_AGENCY_ID= "agency_id";
    public static final String FIELD_ROUTE_SHORT_NAME= "route_short_name";
    public static final String FIELD_ROUTE_LONG_NAME= "route_long_name";
    public static final String FIELD_ROUTE_DESC= "route_desc";
    public static final String FIELD_ROUTE_TYPE= "route_type";
    public static final String FIELD_ROUTE_URL= "route_url";
    public static final String FIELD_ROUTE_COLOR= "route_color";
    public static final String FIELD_ROUTE_TEXT_COLOR= "route_text_color";

    public RoutesFields() {
    }
    
    public String[] getFieldList() {
        String [] fields =  
                {     
                    RoutesFields.FIELD_ROUTE_ID,
                    RoutesFields.FIELD_AGENCY_ID,
                    RoutesFields.FIELD_ROUTE_SHORT_NAME,
                    RoutesFields.FIELD_ROUTE_LONG_NAME,
                    RoutesFields.FIELD_ROUTE_DESC,
                    RoutesFields.FIELD_ROUTE_TYPE,
                    RoutesFields.FIELD_ROUTE_URL,
                    RoutesFields.FIELD_ROUTE_COLOR,
                    RoutesFields.FIELD_ROUTE_TEXT_COLOR
                };
        return fields;
    }
    
}


