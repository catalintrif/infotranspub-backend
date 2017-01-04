package ro.gov.ithub.infotranspub.tools.configurator;
import ro.gov.ithub.infotranspub.tools.configurator.options.BasicOption;
import ro.gov.ithub.infotranspub.tools.configurator.options.OptionHelp;
import ro.gov.ithub.infotranspub.tools.configurator.options.OptionConfigFile;
import org.apache.commons.cli.*;

public class OptionsAggregator {

	private Options options = null;

	public  OptionsAggregator(){
		this.options = new Options();
	}

	public OptionsAggregator append(BasicOption option){
		option.addToOptionList(this.options);
		return this;
	}

	public OptionsAggregator buildHelpConfigOptions(){
		return	this.append(new OptionHelp()).append(new OptionConfigFile());
	}

	public Options getOptions(){
		return options;
	}

}


