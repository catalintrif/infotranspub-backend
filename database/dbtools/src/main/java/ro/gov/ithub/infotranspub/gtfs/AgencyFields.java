package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;


public class AgencyFields implements GTFSFields {
	public static final String FIELD_AGENCY_ID = "agency_id";
	public static final String FIELD_AGENCY_NAME = "agency_name";
	public static final String FIELD_AGENCY_URL = "agency_url";
	public static final String FIELD_AGENCY_TIMEZONE = "agency_timezone";
	public static final String FIELD_AGENCY_LANG = "agency_lang";
	public static final String FIELD_AGENCY_PHONE = "agency_phone";
	public static final String FIELD_AGENCY_FARE_URL = "agency_fare_url";
	public static final String FIELD_AGENCY_EMAIL = "agency_email";

	public AgencyFields() {
	}
	
	public String[] getFieldList() {
		String [] fields =  { 	AgencyFields.FIELD_AGENCY_ID, 
					AgencyFields.FIELD_AGENCY_NAME, 
					AgencyFields.FIELD_AGENCY_URL, 
					AgencyFields.FIELD_AGENCY_TIMEZONE, 
					AgencyFields.FIELD_AGENCY_LANG, 
					AgencyFields.FIELD_AGENCY_PHONE, 
					AgencyFields.FIELD_AGENCY_FARE_URL, 
					AgencyFields.FIELD_AGENCY_EMAIL};
		return fields;
	}
	
}


