package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;

public class StopTimes {

	public static final String FILE_NAME = "stop_times.txt";

	private StopTimesFields fields = null;
	private StopTimesColumns columns = null;
	private StopTimesEntity entity = null;
	
	public StopTimes( StopTimesFields fields,  StopTimesEntity entity, StopTimesColumns columns){
		this.fields = fields;
		this.entity = entity;
		this.columns = columns;
	}

	public StopTimes(){
		this.fields = new StopTimesFields();
		this.entity = new StopTimesEntity();
		this.columns = new StopTimesColumns(this.fields, this.entity);
	}


}


