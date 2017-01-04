package ro.gov.ithub.infotranspub.tools.configurator;
import ro.gov.ithub.infotranspub.tools.configurator.options.OptionHelp;
import ro.gov.ithub.infotranspub.tools.configurator.options.OptionConfigFile;
import org.apache.commons.cli.*;

public class OptionsParser {

	Options options = null;
	CommandLine parsedArguments = null;	
	private static final OptionsParser instance = new OptionsParser();

	protected  OptionsParser(){
	}

	public static OptionsParser getInstance(){
		return instance;
	}

	protected CommandLine parse(String[] args){
			
		CommandLine parsedCommandLine = null;
		try {
			CommandLineParser parser = new DefaultParser();
			parsedCommandLine  = parser.parse( options, args);
		} catch (ParseException e){
			e.printStackTrace();
		}
		return parsedCommandLine;
	}

	public void parseOnce(String[] args, Options opt){
		if (parsedArguments == null ){
			options = opt;
			parsedArguments = parse(args);
			if (parsedArguments == null ) {
				System.exit(1);
			}
			if(parsedArguments.hasOption(OptionHelp.LONG_OPTION) || parsedArguments.hasOption(OptionHelp.SHORT_OPTION)) {
				printHelp();
				System.exit(0);			
			}

			if ( ! (parsedArguments.hasOption(OptionConfigFile.LONG_OPTION) || 
			        parsedArguments.hasOption(OptionConfigFile.SHORT_OPTION) ) ) {
				printHelp();
				System.exit(1);
			}
		}
	}

	public CommandLine getParsedArguments() {
		return parsedArguments;
	}	

	public Options buildOptions(){
		Options acceptedOptions = new Options();
		new OptionHelp().addToOptionList(acceptedOptions);
		new OptionConfigFile().addToOptionList(acceptedOptions);

		return	acceptedOptions;
	}

	public void printHelp(){
		String header = "Do something useful with an input file\n\n";
		String footer = "\nPlease report issues at http://ithub.gov.ro/issues";

		HelpFormatter formatter = new HelpFormatter();
		formatter.printHelp("stoplist", header, options, footer, true);
	}
}


