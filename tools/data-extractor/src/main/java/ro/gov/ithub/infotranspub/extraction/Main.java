package ro.gov.ithub.infotranspub.extraction;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ro.gov.ithub.infotranspub.extraction.config.Configuration;
import ro.gov.ithub.infotranspub.extraction.config.Constants;
import ro.gov.ithub.infotranspub.extraction.scheduler.GenericScheduler;

public class Main {

    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) throws Exception {

        try {
            Configuration.init(args);
        } catch (ParameterException e) {
            failToStart(Configuration.getCmdParser(), e.getMessage(), 1);
        }

        GenericScheduler.getInstance().start();
    }

    private static void failToStart(JCommander cmdParser, String message, int exitCode) {
        logger.error(String.format(Constants.Messages.CANNOT_START, message));
        StringBuilder sb = new StringBuilder();
        cmdParser.usage(sb);
        logger.error(sb.toString());
        System.exit(exitCode);
    }
}
