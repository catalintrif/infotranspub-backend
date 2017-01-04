package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;

public abstract class  SQLColumns {
	protected GTFSFields fields = null;
	
	public String   getSQLColumnList() {
		return SQLColumnsTool.getColumnList(this.fields.getFieldList());
	}

	public String   getValueList() {
		StringBuilder values = new StringBuilder("(");
		values.append(")");
		return values.toString();
	}
	
	public void 	 setFields(GTFSFields fields){
		this.fields = fields;
	}

	public String getTableStructure(){
		return SQLColumnsTool.getTableStructure(this.fields.getFieldList());
	}
}

