package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;
import java.util.HashMap;
import java.util.Set;

public class MetaPool {

	
    	private FieldPool fieldPool = null;
	private ColumnPool columnPool = null;

	public MetaPool(){
		this.createPool();
	}

	private void createPool(){
		this.fieldPool = new FieldPool();
		this.columnPool = new ColumnPool();
		
		Set<String> keys = this.columnPool.getKeys();
		for (String key: keys){
			SQLColumns columns = this.columnPool.getColumns(key);
			GTFSFields fields = this.fieldPool.getFields(key);
			columns.setFields(fields);
		}	
	}

	public SQLColumns getColumns(String entryName){
		return this.columnPool.getColumns(entryName);
	}

	public GTFSFields getFields(String entryName){
		return this.fieldPool.getFields(entryName);
	}
}

