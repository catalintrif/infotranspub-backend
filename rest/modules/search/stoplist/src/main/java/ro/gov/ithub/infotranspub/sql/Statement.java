package ro.gov.ithub.infotranspub.sql;
import java.io.IOException;
import java.sql.SQLException;

public class Statement{
	private java.sql.Statement sqlStatement = null;
	public Statement(java.sql.Statement sqlStatement){
		this.sqlStatement = sqlStatement;
	}

	public java.sql.Statement getRealStatement(){
		return this.sqlStatement;
	}


      public ResultSet executeQuery(String query){
                ResultSet resultSet = null;
                try {
                        java.sql.ResultSet rs = sqlStatement.executeQuery(query);
                        resultSet = new ResultSet(rs);  

		    } catch (SQLException e ) {
			e.printStackTrace();
		    }   
                return resultSet;
	}
}


