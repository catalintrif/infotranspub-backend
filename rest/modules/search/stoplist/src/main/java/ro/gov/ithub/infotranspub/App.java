package ro.gov.ithub.infotranspub;
import ro.gov.ithub.infotranspub.tools.configurator.OptionsParser;
import ro.gov.ithub.infotranspub.tools.configurator.ConfigProps;
import ro.gov.ithub.infotranspub.tools.configurator.OptionsAggregator;
import ro.gov.ithub.infotranspub.tools.configurator.options.OptionConfigFile;
import ro.gov.ithub.infotranspub.sql.DataSource;
import ro.gov.ithub.infotranspub.sql.Connection;
import ro.gov.ithub.infotranspub.sql.Statement;
import ro.gov.ithub.infotranspub.sql.ResultSet;
import org.apache.commons.cli.*;
import org.apache.commons.cli.DefaultParser;
import java.sql.SQLException;

public class App {
    public static void main( String[] args ) throws ParseException {

	OptionsAggregator aggregator = new OptionsAggregator();
	aggregator.buildHelpConfigOptions().append(new OptionHello());;
	Options options = aggregator.getOptions();	

	OptionsParser.getInstance().parseOnce(args, options);
	
	String configFile = OptionsParser.getInstance().getParsedArguments().getOptionValue(OptionConfigFile.LONG_OPTION);
	ConfigProps.getInstance().load(configFile);	
		
	Connection connection = new Connection();
	Statement statement = connection.createStatement();	
	ResultSet resultSet = statement.executeQuery("SELECT * from t_stops");

	try {
        while (resultSet.getRealResultSet().next()) {
            String name = resultSet.getRealResultSet().getString("name");
		System.out.println(name);
        }
	statement.getRealStatement().close();
    } catch (SQLException e ) {
        e.printStackTrace();
	}	

    }
}


