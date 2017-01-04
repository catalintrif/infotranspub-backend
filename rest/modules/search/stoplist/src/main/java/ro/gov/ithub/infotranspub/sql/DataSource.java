package ro.gov.ithub.infotranspub.sql;
import java.io.IOException;
import java.sql.SQLException;
import ro.gov.ithub.infotranspub.tools.configurator.ConfigProps;

import org.apache.commons.dbcp.BasicDataSource;


public class DataSource {

    private static DataSource     datasource;
    private BasicDataSource ds;
    private DataSource() throws IOException, SQLException {
        ds = new BasicDataSource();
	
        ds.setDriverClassName(ConfigProps.getInstance().getProperty("database.driver"));
        ds.setUsername(ConfigProps.getInstance().getProperty("database.credentials.login"));
        ds.setPassword(ConfigProps.getInstance().getProperty("database.credentials.password"));
        ds.setUrl(ConfigProps.getInstance().getProperty("database.url"));

     // the settings below are optional -- dbcp can work with defaults
        //ds.setMinIdle(5);
        //--ds.setMaxActive(20);
        //ds.setMaxIdle(20);
        //ds.setMaxOpenPreparedStatements(180);

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

}

