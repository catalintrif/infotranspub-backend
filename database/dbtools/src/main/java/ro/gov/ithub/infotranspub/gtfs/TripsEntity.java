package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;

public class TripsEntity {

	private int id = 0;
	

		

	public TripsEntity() {
		this.id = 0;
	}

	
	protected void setId (int id){
		this.id = id;
	}

	public int 	getId(){ return id; }
}


