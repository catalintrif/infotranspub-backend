package ro.gov.ithub.infotranspub.tools.configurator.options;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.OptionBuilder;
public abstract class BasicOption{
	public BasicOption(){
	}
	
		

	public void addToOptionList(Options optionList){
		if (hasLongOption() ){
			if (hasShortOption()) {
				optionList.addOption( getShortOption(), getLongOption(),hasArg(), getDescription() );
			} else {
				if (hasArg()) {
					optionList.addOption( OptionBuilder.withLongOpt(getLongOption())
						.hasArg()
						.withValueSeparator()
						.withDescription(getDescription())
						.create());
				} else {
					optionList.addOption( OptionBuilder.withLongOpt(getLongOption())
						.withDescription(getDescription())
						.create());
				}
			}
		} else if (hasShortOption()) {
			optionList.addOption( getShortOption(), hasArg(), getDescription() );
		}	
	}

	public abstract String getShortOption();
	public abstract String getLongOption();
	public abstract Boolean hasShortOption();
	public abstract Boolean hasLongOption();
	public abstract Boolean hasArg();
	public abstract String getDescription();
}
