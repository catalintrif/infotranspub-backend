package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;

public class Transfers {

	public static final String FILE_NAME = "transfers.txt";

	private TransfersFields fields = null;
	private TransfersColumns columns = null;
	private TransfersEntity entity = null;
	
	public Transfers( TransfersFields fields,  TransfersEntity entity, TransfersColumns columns){
		this.fields = fields;
		this.entity = entity;
		this.columns = columns;
	}

	public Transfers(){
		this.fields = new TransfersFields();
		this.entity = new TransfersEntity();
		this.columns = new TransfersColumns(this.fields, this.entity);
	}


}


