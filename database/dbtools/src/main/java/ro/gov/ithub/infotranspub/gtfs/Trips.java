package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;

public class Trips {

	public static final String FILE_NAME = "trips.txt";

	private TripsFields fields = null;
	private TripsColumns columns = null;
	private TripsEntity entity = null;
	
	public Trips( TripsFields fields,  TripsEntity entity, TripsColumns columns){
		this.fields = fields;
		this.entity = entity;
		this.columns = columns;
	}

	public Trips(){
		this.fields = new TripsFields();
		this.entity = new TripsEntity();
		this.columns = new TripsColumns(this.fields, this.entity);
	}


}


