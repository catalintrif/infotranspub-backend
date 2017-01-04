package ro.gov.ithub.infotranspub.tools.configurator.options;

public class OptionConfigFile extends BasicOption{

	public static final  String SHORT_OPTION ="c";	
	public static final  String LONG_OPTION = "config-file";	
	public static final  Boolean HAS_ARG = true;	
	public static final  String DESCRIPTION="Sets a config file different from the default one";	
	
	public OptionConfigFile(){

	}
	@Override
	public  String getShortOption() { 
		return OptionConfigFile.SHORT_OPTION;
	}

	@Override
	public  String getLongOption() { 
		return OptionConfigFile.LONG_OPTION;
	}

	@Override
	public  Boolean hasShortOption() { 
		return  (OptionConfigFile.SHORT_OPTION.equals("") == false);
	}

	@Override
	public  Boolean hasLongOption() { 
		return  (OptionConfigFile.LONG_OPTION.equals("") == false);
	}

	@Override
	public  Boolean hasArg() { 
		return OptionConfigFile.HAS_ARG;
	}

	@Override
	public  String getDescription() { 
		return OptionConfigFile.DESCRIPTION;
	}
}
