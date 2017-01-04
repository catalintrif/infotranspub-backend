package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;

public class CalendarDatesFields implements GTFSFields {
    public static final String FIELD_SERVICE_ID = "service_id";
    public static final String FIELD_DATE = "date";
    public static final String FIELD_EXCEPTION_TYPE = "exception_type";

    public CalendarDatesFields() {
    }
    
    public String[] getFieldList() {
        String [] fields =  
                {     
                    CalendarDatesFields.FIELD_SERVICE_ID, 
                    CalendarDatesFields.FIELD_DATE, 
                    CalendarDatesFields.FIELD_EXCEPTION_TYPE 
                };
        return fields;
    }
    
}


