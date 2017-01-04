package ro.gov.ithub.infotranspub.tools.configurator.options;

public class OptionHelp extends BasicOption{

	public static final  String SHORT_OPTION ="h";	
	public static final  String LONG_OPTION = "help";	
	public static final  Boolean HAS_ARG = false;	
	public static final  String DESCRIPTION="Shows this help message";	
	
	public OptionHelp(){

	}
	@Override
	public  String getShortOption() { 
		return OptionHelp.SHORT_OPTION;
	}

	@Override
	public  String getLongOption() { 
		return OptionHelp.LONG_OPTION;
	}

	@Override
	public  Boolean hasShortOption() { 
		return  (OptionHelp.SHORT_OPTION.equals("") == false);
	}

	@Override
	public  Boolean hasLongOption() { 
		return  (OptionHelp.LONG_OPTION.equals("") == false);
	}

	@Override
	public  Boolean hasArg() { 
		return OptionHelp.HAS_ARG;
	}

	@Override
	public  String getDescription() { 
		return OptionHelp.DESCRIPTION;
	}
}
