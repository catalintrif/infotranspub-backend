package ro.gov.ithub.infotranspub.extraction.config;

/**
 * Created by vlad4800@gmail.com on 13-Dec-16.
 */
public class Constants {

    public final class Messages {
        public static final String CANNOT_START = "Error running data-extractor: %s";
        public static final String START_JOB = "Started job: %s";
        public static final String END_JOB = "Ended job: %s";
        public static final String FAILED_TO_PARSE_LINE = "Failed to parse line information on  %s";
        public static final String LINE_EXISTS_GOT_CACHE = "Line exists, but using cache to load data ...";
        public static final String LINE_EXISTS_NO_INFO = "Line exists ... lineInformation is null ...";
        public static final String PARSING_LINE = "Parsing line = %s";
    }

    public final class JobNames {

        public static final String RATB = "RATBJob";
    }
}
