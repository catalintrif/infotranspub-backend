package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;

public class Frequencies {

	public static final String FILE_NAME = "frequencies.txt";

	private FrequenciesFields fields = null;
	private FrequenciesColumns columns = null;
	private FrequenciesEntity entity = null;
	
	public Frequencies( FrequenciesFields fields,  FrequenciesEntity entity, FrequenciesColumns columns){
		this.fields = fields;
		this.entity = entity;
		this.columns = columns;
	}

	public Frequencies(){
		this.fields = new FrequenciesFields();
		this.entity = new FrequenciesEntity();
		this.columns = new FrequenciesColumns(this.fields, this.entity);
	}


}


