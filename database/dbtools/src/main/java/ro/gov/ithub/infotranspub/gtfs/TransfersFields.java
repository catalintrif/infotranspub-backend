package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;


public class TransfersFields implements GTFSFields {
    public static final String FIELD_FROM_STOP_ID = "from_stop_id";
    public static final String FIELD_TO_STOP_ID = "to_stop_id";
    public static final String FIELD_TRANSFER_TYPE = "transfer_type";
    public static final String FIELD_MIN_TRANSFER_TIME = "min_transfer_time";

    public TransfersFields() {
    }
    
    public String[] getFieldList() {
        String [] fields =  
                {     
                    TransfersFields.FIELD_FROM_STOP_ID, 
                    TransfersFields.FIELD_TO_STOP_ID, 
                    TransfersFields.FIELD_TRANSFER_TYPE, 
                    TransfersFields.FIELD_MIN_TRANSFER_TIME 
                };
        return fields;
    }
    
}


