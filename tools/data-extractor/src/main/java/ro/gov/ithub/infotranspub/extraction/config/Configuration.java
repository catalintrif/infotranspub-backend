package ro.gov.ithub.infotranspub.extraction.config;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ro.gov.ithub.infotranspub.extraction.Main;
import ro.gov.ithub.infotranspub.extraction.extractor.CrawlerFactory;
import ro.gov.ithub.infotranspub.extraction.extractor.GenericCrawler;
import ro.gov.ithub.infotranspub.extraction.gps.GpsDataFixer;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;

/**
 * Created by vlad4800@gmail.com on 13-Dec-16.
 */
public class Configuration {

    private static final Logger logger = LogManager.getLogger(Main.class);

    private static final String PROGRAM_NAME = "data-extractor";

    private static final String CACHE_SUB_PATH = "extracted";
    private static final RunParameters params = new RunParameters();
    private static final JCommander cmdParser;
    private static GenericCrawler crawler = null;
    private static File cacheFolder = null;
    private static GpsDataFixer dataFixer = null;

    static {
        cmdParser = new JCommander(params);
    }

    public static void init(String[] args) throws ParameterException {
        cmdParser.setProgramName(PROGRAM_NAME);
        cmdParser.parse(args);

        if (params.help) {
            printUsage(cmdParser);
        }

        printParameters(params);

        // TODO Move this to it's corresponding Job in Scheduler?
        crawler = CrawlerFactory.createCrawler(params.extractor);
        try {
            cacheFolder = createCacheFolder(params.cacheFolder, params.extractor);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (params.fixGpsCoordinates) {
            dataFixer = new GpsDataFixer();
        }
    }

    public static GenericCrawler getCrawler() {
        return crawler;
    }

    public static File getCacheFolder() {
        return cacheFolder;
    }

    public static GpsDataFixer getDataFixer() {
        return dataFixer;
    }

    public static RunParameters getParams() {
        return params;
    }

    public static JCommander getCmdParser() {
        return cmdParser;
    }

    /**
     * Move this to {@link ro.gov.ithub.infotranspub.extraction.Util}?
     * @param cachePath
     * @param extractorName
     * @return
     * @throws IOException
     */
    private static File createCacheFolder(String cachePath, String extractorName) throws IOException {
        File cachePathFile = new File(cachePath);
        if (cachePathFile.canWrite()) {
            File cacheFolder = new File(new File(cachePathFile, extractorName), CACHE_SUB_PATH);
            if (cacheFolder.exists()) {
                if (cacheFolder.canWrite()) {
                    return cacheFolder;
                } else {
                    throw new IOException("Unable to write in " + cacheFolder.getAbsolutePath());
                }
            } else {
                if (cacheFolder.mkdirs()) {
                    return cacheFolder;
                } else {
                    throw new IOException("Unable to create cache folder at " + cacheFolder.getAbsolutePath());
                }
            }
        } else {
            throw new IOException("Unable to write in " + cachePathFile.getAbsolutePath());
        }
    }


    /**
     *
     * @param cmdParser
     */
    private static void printUsage(JCommander cmdParser) {
        StringBuilder sb = new StringBuilder();
        cmdParser.usage(sb);
        logger.info(sb.toString());
        System.exit(0);
    }

    /**
     *
     * @param runParameters
     */
    private static void printParameters(RunParameters runParameters) {
        StringBuilder sb = new StringBuilder();
        for (Field field : runParameters.getClass().getDeclaredFields()) {
            try {
                sb.append("\t").append(field.getName()).append(" = ").append(field.get(runParameters)).append("\n");
            } catch (IllegalAccessException e) {
                //-- ignore
            }
        }
        logger.info("Service parameters: \n" + sb.toString());
    }

    public static class RunParameters {
        @Parameter(names = {"-h", "--help"}, description = "Print this help", help = true, echoInput = true)
        public boolean help = false;

        @Parameter(names = {"--extractor"}, description = "The extractor to run: ratb")
        public String extractor = "ratb";

        @Parameter(names = {"--update"}, description = "Update existing data")
        public boolean updateExisting = false;

        @Parameter(names = {"--useCache"}, description = "Use cached version instead of the live one, if it exists")
        public boolean useCache = true;

        @Parameter(names = {"--cacheFolder"}, description = "Cache folder")
        public String cacheFolder = "data";

        @Parameter(names = {"--fixGpsCoordinates"}, description = "Fix the missing GPS coordinates")
        public boolean fixGpsCoordinates = true;
    }
}
