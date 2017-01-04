package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;


public class CalendarFields implements GTFSFields {
    public static final String FIELD_SERVICE_ID = "service_id";
    public static final String FIELD_MONDAY = "monday";
    public static final String FIELD_TUESDAY = "tuesday";
    public static final String FIELD_WEDNESDAY = "wednesday";
    public static final String FIELD_THURSDAY = "thursday";
    public static final String FIELD_FRIDAY = "friday";
    public static final String FIELD_SATURDAY = "saturday";
    public static final String FIELD_SUNDAY = "sunday";
    public static final String FIELD_START_DATE = "start_date";
    public static final String FIELD_END_DATE = "end_date";

    public CalendarFields() {
    }
    
    public String[] getFieldList() {
        String [] fields =  
                {     
                    CalendarFields.FIELD_SERVICE_ID, 
                    CalendarFields.FIELD_MONDAY, 
                    CalendarFields.FIELD_TUESDAY, 
                    CalendarFields.FIELD_WEDNESDAY, 
                    CalendarFields.FIELD_THURSDAY, 
                    CalendarFields.FIELD_FRIDAY, 
                    CalendarFields.FIELD_SATURDAY, 
                    CalendarFields.FIELD_SUNDAY, 
                    CalendarFields.FIELD_START_DATE, 
                    CalendarFields.FIELD_END_DATE
                };
        return fields;
    }
    
}


