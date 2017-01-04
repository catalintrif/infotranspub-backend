package ro.gov.ithub.infotranspub.dbtools.processors;
import ro.gov.ithub.infotranspub.gtfs.MetaPool;
import ro.gov.ithub.infotranspub.sql.DataSource;
import ro.gov.ithub.infotranspub.sql.DataSource;
import ro.gov.ithub.infotranspub.dbtools.text.CSVLineTool;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;
import java.io.IOException;
import java.lang.String;
import java.lang.StringBuilder;
import java.util.List;
import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

public class SQLTempTableProcessor implements GenericProcessor{

	private static final int BULK_INSERT_SIZE = 500;
	private static final String CREATE_TEMP_TABLE_PREFIX="CREATE TABLE TMP_";
	private static final String INSERT_TEMP_TABLE_PREFIX="INSERT INTO TMP_";
	private static final String SQL_COLUMN_SEPARATOR=",";

	private int lineCount = 0;
    	private String []  header = null;
	private String entryName = null;
	private String tableRadix = null;
	private Boolean skipIt = false;
	private java.sql.Connection sqlConnection = null;
	private java.sql.Statement sqlStatement= null;	
	private MetaPool metaPool = new MetaPool();

	private StringBuilder inserter = null;
	private StringBuilder multipleValues = null;
	private StringBuilder sqlQuery = null;
	
	public SQLTempTableProcessor(String entryName) {
		this.entryName = entryName;
		String[] name = entryName.split("\\.");
		this.tableRadix = name[0];
		allocateSQLResources();
	}

	@Override
	public Boolean process(){
		return false;
	}
	@Override
	public Boolean process(String line){
		return false;
	}

	public void processHeader(String line){
		
		String  tableStructure = this.metaPool.getColumns(entryName).getTableStructure();
		StringBuilder createTable = new StringBuilder(SQLTempTableProcessor.CREATE_TEMP_TABLE_PREFIX + this.tableRadix + tableStructure);
		 
		System.out.println(createTable.toString());
		executeUpdate(createTable.toString());
		System.out.println(this.entryName);

		
	    	inserter = new StringBuilder(SQLTempTableProcessor.INSERT_TEMP_TABLE_PREFIX + this.tableRadix + "(" );
            	header = line.split(SQLTempTableProcessor.SQL_COLUMN_SEPARATOR);
		inserter.append(StringUtils.join(header,SQLTempTableProcessor.SQL_COLUMN_SEPARATOR));
		inserter.append(") VALUES ");
	}

	public void processBody(String line){
		List<String> grownArray = CSVLineTool.trimElements(
						CSVLineTool.quoteAwareSplit(
							CSVLineTool.simpleQuoteRepeate(line)));
		String [] cells = CSVLineTool.toStringArray(
					CSVLineTool.growArrayList(grownArray,header.length - grownArray.size()));
		
		if ((this.lineCount % SQLTempTableProcessor.BULK_INSERT_SIZE) == 0){
	        	sqlQuery = new StringBuilder(inserter.toString());
		}

                if ((this.lineCount % SQLTempTableProcessor.BULK_INSERT_SIZE) > 0 ){
			sqlQuery.append(SQLTempTableProcessor.SQL_COLUMN_SEPARATOR);
		}
		
		StringBuilder sqlValueRow = new StringBuilder("('");
		sqlValueRow.append(StringUtils.join(cells,"','"));
		sqlValueRow.append("')");

		sqlQuery.append(sqlValueRow.toString());
		sqlQuery.append("\n");

                if ((this.lineCount > 0) && (((this.lineCount+1)% SQLTempTableProcessor.BULK_INSERT_SIZE) == 0)){ 
			executeUpdate(sqlQuery.toString());
               } 
		this.lineCount++;	
	}

	public void processTail(){
                if (  (this.lineCount > 0) 
                    &&( ((this.lineCount+1) % SQLTempTableProcessor.BULK_INSERT_SIZE) != 0)){ 
			executeUpdate(sqlQuery.toString());
               } 
	
		releaseSQLResources();	
	}


	public Boolean executeUpdate(String query){
		Boolean success = true;
		if ( sqlStatement != null) {
			try {
				sqlStatement.executeUpdate(query);
			} catch (SQLException e ) {
				e.printStackTrace();
				success = false;
			}
		}
		return success;
	}



	public Boolean allocateSQLResources(){
		Boolean success = true;
		try {
			DataSource dataSource = DataSource.getInstance();
			this.sqlConnection = dataSource.getConnection();
			this.sqlStatement = sqlConnection.createStatement();
		} catch ( SQLException e){
			e.printStackTrace();
			success = false;
		} catch (IOException e){
			e.printStackTrace();
			success = false;
		}
		return success;
	}

	public Boolean releaseSQLResources(){
		Boolean success = true;
		
		if ( this.sqlStatement != null) {
			try {
				this.sqlStatement.close();
			} catch (SQLException e ) {
				e.printStackTrace();
				success = false;
			}
		}
	
		if ( this.sqlConnection != null) {
			try {
				this.sqlConnection.close();
			} catch (SQLException e ) {
				e.printStackTrace();
				success = false;
			}
		}

		return success;
	}
}
