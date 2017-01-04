package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;

public class AgencyEntity {

	private int id = 0;
	private String agencyId = null;
	private String agencyName = null;
	private String agencyURL = null;
	private String agencyTimeZone = null;
	private String agencyLang = null;
	private String agencyPhone = null;
	

		

	public AgencyEntity( String agencyId, String agencyName, String agencyURL, String agencyTimeZone, String agencyLang, String agencyPhone) {
		this.id = 0;
		this.agencyId = agencyId;
		this.agencyName = agencyName;
		this.agencyURL = agencyURL;
		this.agencyTimeZone = agencyTimeZone;
		this.agencyLang = agencyLang;
		this.agencyPhone = agencyPhone;
	}

	public AgencyEntity() {
		this.id = 0;
		this.agencyId = null;
		this.agencyName = null;
		this.agencyURL = null;
		this.agencyTimeZone = null;
		this.agencyLang = null;
		this.agencyPhone = null;
	}

	
	protected void setId (int id){
		this.id = id;
	}

	public int 	getId(){ return id; }
	public String 	getAgencyId() { return agencyId; }
	public String 	getAgencyName() { return agencyName; }
	public String 	getAgencyURL() { return agencyURL; }
	public String 	getAgencyTimeZone() { return agencyTimeZone; }
	public String 	getAgencyLang() { return agencyLang; }
	public String 	getAgencyPhone() { return agencyPhone; }
}


