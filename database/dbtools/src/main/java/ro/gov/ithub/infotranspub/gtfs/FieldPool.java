package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;
import java.util.HashMap;
import java.util.Set;

public class FieldPool {

	
    	private HashMap<String,GTFSFields> pool = new HashMap<String,GTFSFields>();

	public FieldPool(){
		this.createPool();
	}

	private void createPool(){
		this.pool.put(Agency.FILE_NAME,new AgencyFields());
		this.pool.put(Stops.FILE_NAME,new StopsFields());
		this.pool.put(Calendar.FILE_NAME,new CalendarFields());
		this.pool.put(CalendarDates.FILE_NAME,new CalendarDatesFields());
		this.pool.put(FareAttributes.FILE_NAME,new FareAttributesFields());
		this.pool.put(FareRules.FILE_NAME,new FareRulesFields());
		this.pool.put(FeedInfo.FILE_NAME,new FeedInfoFields());
		this.pool.put(Frequencies.FILE_NAME,new FrequenciesFields());
		this.pool.put(Routes.FILE_NAME,new RoutesFields());
		this.pool.put(Shapes.FILE_NAME,new ShapesFields());
		this.pool.put(StopTimes.FILE_NAME,new StopTimesFields());
		this.pool.put(Trips.FILE_NAME,new TripsFields());
		this.pool.put(Transfers.FILE_NAME,new TransfersFields());
	}

	public GTFSFields getFields(String fileName){
		if (this.pool.containsKey(fileName)){

			return this.pool.get(fileName);
		}
		return null;
	}

	public Set<String> getKeys(){
                return this.pool.keySet();
        }
}

