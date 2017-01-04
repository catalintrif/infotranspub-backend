package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;



public class FareAttributesFields implements GTFSFields {
    public static final String FIELD_FARE_ID = "fare_id";
    public static final String FIELD_PRICE = "price";
    public static final String FIELD_CURRENCY_TYPE = "currency_type";
    public static final String FIELD_PAYMENT_METHOD = "payment_method";
    public static final String FIELD_TRANSFERS = "transfers";
    public static final String FIELD_TRANSFER_DURATION = "transfer_duration";

    public FareAttributesFields() {
    }
    
    public String[] getFieldList() {
        String [] fields =  
                {     
                    FareAttributesFields.FIELD_FARE_ID, 
                    FareAttributesFields.FIELD_PRICE, 
                    FareAttributesFields.FIELD_CURRENCY_TYPE, 
                    FareAttributesFields.FIELD_PAYMENT_METHOD, 
                    FareAttributesFields.FIELD_TRANSFERS, 
                    FareAttributesFields.FIELD_TRANSFER_DURATION
                };
        return fields;
    }
    
}


