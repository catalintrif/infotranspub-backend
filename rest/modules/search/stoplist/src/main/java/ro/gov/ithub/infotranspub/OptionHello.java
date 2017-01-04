package ro.gov.ithub.infotranspub;

import ro.gov.ithub.infotranspub.tools.configurator.options.BasicOption;

public class OptionHello extends BasicOption{

        public static final  String SHORT_OPTION ="";  
        public static final  String LONG_OPTION = "hello";       
        public static final  Boolean HAS_ARG = false;   
        public static final  String DESCRIPTION="Shows a hello";      
        
        public OptionHello(){

        }
        @Override
        public  String getShortOption() { 
                return OptionHello.SHORT_OPTION;
        }

        @Override
        public  String getLongOption() { 
                return OptionHello.LONG_OPTION;
        }

        @Override
        public  Boolean hasShortOption() { 
                return  (OptionHello.SHORT_OPTION.equals("") == false);
        }

        @Override
        public  Boolean hasLongOption() { 
                return  (OptionHello.LONG_OPTION.equals("") == false);
        }

        @Override
        public  Boolean hasArg() { 
                return OptionHello.HAS_ARG;
        }

        @Override
        public  String getDescription() { 
                return OptionHello.DESCRIPTION;
        }
}

