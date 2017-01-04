package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;

public class Agency {

	public static final String FILE_NAME = "agency.txt";

	private AgencyFields fields = null;
	private AgencyColumns columns = null;
	private AgencyEntity entity = null;
	
	public Agency( AgencyFields fields,  AgencyEntity entity, AgencyColumns columns){
		this.fields = fields;
		this.entity = entity;
		this.columns = columns;
	}

	public Agency(){
		this.fields = new AgencyFields();
		this.entity = new AgencyEntity();
		this.columns = new AgencyColumns(this.fields, this.entity);
	}


}


