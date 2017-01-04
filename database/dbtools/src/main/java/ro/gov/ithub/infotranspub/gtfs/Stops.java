package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;

public class Stops {

	public static final String FILE_NAME = "stops.txt";

	private StopsFields fields = null;
	private StopsColumns columns = null;
	private StopsEntity entity = null;
	
	public Stops( StopsFields fields,  StopsEntity entity, StopsColumns columns){
		this.fields = fields;
		this.entity = entity;
		this.columns = columns;
	}

	public Stops(){
		this.fields = new StopsFields();
		this.entity = new StopsEntity();
		this.columns = new StopsColumns(this.fields, this.entity);
	}


}


