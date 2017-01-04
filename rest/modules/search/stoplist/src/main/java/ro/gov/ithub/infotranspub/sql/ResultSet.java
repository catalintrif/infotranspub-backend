package ro.gov.ithub.infotranspub.sql;
import java.io.IOException;
import java.sql.SQLException;

public class ResultSet{
	private java.sql.ResultSet sqlResultSet = null;;
	public ResultSet(java.sql.ResultSet sqlResultSet){
		this.sqlResultSet = sqlResultSet;
	}

	public java.sql.ResultSet getRealResultSet(){
		return this.sqlResultSet;
	}

}


