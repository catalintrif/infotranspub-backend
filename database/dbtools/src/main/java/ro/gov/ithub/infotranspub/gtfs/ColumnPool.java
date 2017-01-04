package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;
import java.util.HashMap;
import java.util.Set;

public class ColumnPool {

	
    	private HashMap<String,SQLColumns> pool = new HashMap<String,SQLColumns>();

	public ColumnPool(){
		this.createPool();
	}

	private void createPool(){
		this.pool.put(Agency.FILE_NAME,new AgencyColumns(null,null));
		this.pool.put(Stops.FILE_NAME,new StopsColumns(null,null));
		this.pool.put(Calendar.FILE_NAME,new CalendarColumns(null,null));
		this.pool.put(CalendarDates.FILE_NAME,new CalendarDatesColumns(null,null));
		this.pool.put(FareAttributes.FILE_NAME,new FareAttributesColumns(null,null));
		this.pool.put(FareRules.FILE_NAME,new FareRulesColumns(null,null));
		this.pool.put(FeedInfo.FILE_NAME,new FeedInfoColumns(null,null));
		this.pool.put(Frequencies.FILE_NAME,new FrequenciesColumns(null,null));
		this.pool.put(Routes.FILE_NAME,new RoutesColumns(null,null));
		this.pool.put(Shapes.FILE_NAME,new ShapesColumns(null,null));
		this.pool.put(StopTimes.FILE_NAME,new StopTimesColumns(null,null));
		this.pool.put(Trips.FILE_NAME,new TripsColumns(null,null));
		this.pool.put(Transfers.FILE_NAME,new TransfersColumns(null,null));

	}

	public SQLColumns getColumns(String fileName){
		if (this.pool.containsKey(fileName)){
			return this.pool.get(fileName);
		}
		return null;
	}

	public Set<String> getKeys(){
		return this.pool.keySet();
	}
}

