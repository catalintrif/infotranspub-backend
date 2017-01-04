package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;

public class FareRules {

	public static final String FILE_NAME = "fare_rules.txt";

	private FareRulesFields fields = null;
	private FareRulesColumns columns = null;
	private FareRulesEntity entity = null;
	
	public FareRules( FareRulesFields fields,  FareRulesEntity entity, FareRulesColumns columns){
		this.fields = fields;
		this.entity = entity;
		this.columns = columns;
	}

	public FareRules(){
		this.fields = new FareRulesFields();
		this.entity = new FareRulesEntity();
		this.columns = new FareRulesColumns(this.fields, this.entity);
	}


}


