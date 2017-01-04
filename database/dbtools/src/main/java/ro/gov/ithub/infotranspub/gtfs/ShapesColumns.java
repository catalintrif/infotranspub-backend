package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;


public class ShapesColumns extends  SQLColumns {

	private ShapesEntity entity = null;

	public ShapesColumns(GTFSFields fields, ShapesEntity entity){
		this.fields = fields;
		this.entity = entity;
	}

	public void setEntity(ShapesEntity entity){
		this.entity = entity;
	}

		 
}


