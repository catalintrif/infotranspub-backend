package ro.gov.ithub.infotranspub.sql;
import java.io.IOException;
import java.sql.SQLException;


public class Connection{
	private java.sql.Connection sqlConnection = null;;
	public Connection(){
		try {
			this.sqlConnection = DataSource.getInstance().getConnection();
		} catch (SQLException e){
			e.printStackTrace();
		} catch (IOException e){
			e.printStackTrace();
		}
	}

	public Connection(java.sql.Connection sqlConnection){
		this.sqlConnection = sqlConnection;
	}

	public java.sql.Connection getRealConnection(){
		return this.sqlConnection;
	}

	public Boolean hasConnection(){
		return this.sqlConnection == null;
	}

	public Statement createStatement(){
		Statement newStatement = null;
		try {
			newStatement = new Statement(getRealConnection().createStatement());
		} catch(SQLException e){
			e.printStackTrace();
		}		
		return newStatement;
	}

	
}


