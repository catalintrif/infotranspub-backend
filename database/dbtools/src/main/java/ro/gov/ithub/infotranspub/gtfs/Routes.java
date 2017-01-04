package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;

public class Routes {

	public static final String FILE_NAME = "routes.txt";

	private RoutesFields fields = null;
	private RoutesColumns columns = null;
	private RoutesEntity entity = null;
	
	public Routes( RoutesFields fields,  RoutesEntity entity, RoutesColumns columns){
		this.fields = fields;
		this.entity = entity;
		this.columns = columns;
	}

	public Routes(){
		this.fields = new RoutesFields();
		this.entity = new RoutesEntity();
		this.columns = new RoutesColumns(this.fields, this.entity);
	}


}


