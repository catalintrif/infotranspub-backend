package ro.gov.ithub.infotranspub.tools.configurator;
import ro.gov.ithub.infotranspub.tools.configurator.options.*;
import org.apache.commons.cli.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;


public class ConfigProps {

	Options options = null;
	CommandLine parsedArguments = null;	
	private static final ConfigProps instance = new ConfigProps();
	private Properties properties = new Properties();

		
	protected  ConfigProps(){
	}

	public static ConfigProps getInstance(){
		return instance;
	}

	public String getProperty(String key){
		String value = properties.getProperty(key);
		return value;
	}	

	public void load(String propsFilePath){
                try {
                        File file = new File(propsFilePath);
                        FileInputStream fileInput = new FileInputStream(file);
                        properties.load(fileInput);
                        fileInput.close();

                } catch (FileNotFoundException e) {
                        e.printStackTrace();
                } catch (IOException e) {
                        e.printStackTrace();
                }               
			
	}

}


