package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;

public class Shapes {

	public static final String FILE_NAME = "shapes.txt";

	private ShapesFields fields = null;
	private ShapesColumns columns = null;
	private ShapesEntity entity = null;
	
	public Shapes( ShapesFields fields,  ShapesEntity entity, ShapesColumns columns){
		this.fields = fields;
		this.entity = entity;
		this.columns = columns;
	}

	public Shapes(){
		this.fields = new ShapesFields();
		this.entity = new ShapesEntity();
		this.columns = new ShapesColumns(this.fields, this.entity);
	}


}


