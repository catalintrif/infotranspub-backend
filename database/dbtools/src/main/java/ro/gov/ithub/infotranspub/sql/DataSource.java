package ro.gov.ithub.infotranspub.sql;
import java.io.IOException;
import java.sql.SQLException;

import org.apache.commons.dbcp.BasicDataSource;


public class DataSource {

    private static DataSource     datasource;
    private BasicDataSource ds;

    private DataSource() throws IOException, SQLException {
        ds = new BasicDataSource();
        ds.setDriverClassName("org.postgresql.Driver");
        ds.setUsername("alex");
        ds.setPassword("");
        ds.setUrl("jdbc:postgresql://localhost/infotranspub");
       
     // the settings below are optional -- dbcp can work with defaults
     ds.setMinIdle(1);// 5
     ds.setMaxActive(1); // 10
     ds.setMaxIdle(1); // 20
     ds.setMaxOpenPreparedStatements(1); // 180

    }

    public static DataSource getInstance() throws IOException, SQLException{
        if (datasource == null) {
            datasource = new DataSource();
            return datasource;
        } else {
            return datasource;
        }
    }

    public java.sql.Connection getConnection() throws SQLException {
        return this.ds.getConnection();
    }

    public void close() throws SQLException {
        this.ds.close();
    }
}
