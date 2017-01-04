package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;

public class FareAttributes {

	public static final String FILE_NAME = "fare_attributes.txt";

	private FareAttributesFields fields = null;
	private FareAttributesColumns columns = null;
	private FareAttributesEntity entity = null;
	
	public FareAttributes( FareAttributesFields fields,  FareAttributesEntity entity, FareAttributesColumns columns){
		this.fields = fields;
		this.entity = entity;
		this.columns = columns;
	}

	public FareAttributes(){
		this.fields = new FareAttributesFields();
		this.entity = new FareAttributesEntity();
		this.columns = new FareAttributesColumns(this.fields, this.entity);
	}


}


