package ro.gov.ithub.infotranspub.extraction.scheduler.jobs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import ro.gov.ithub.infotranspub.extraction.config.Constants;
import ro.gov.ithub.infotranspub.extraction.data.Agency;
import ro.gov.ithub.infotranspub.extraction.data.Line;
import ro.gov.ithub.infotranspub.extraction.data.LineType;
import ro.gov.ithub.infotranspub.extraction.output.JsonFileCache;
import ro.gov.ithub.infotranspub.extraction.scheduler.GenericJob;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by vlad4800@gmail.com on 13-Dec-16.
 * <p>
 * Job parses data from RATB
 */
public class RATBJob extends GenericJob {

    private static final Logger logger = LogManager.getLogger(RATBJob.class);

    public RATBJob() {
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info(String.format(Constants.Messages.START_JOB, Constants.JobNames.RATB));

        Agency agency = crawler.getAgency();

        try {
            for (LineType type : LineType.values()) {
                List<String> lineIds = null;

                lineIds = crawler.parseLinesIds(type);

                logger.info(type + " = " + lineIds);

                JsonFileCache fileCache = new JsonFileCache(cacheFolder.getAbsolutePath() + File.separator + "%s.json");
                for (String lineId : lineIds) {
                    logger.info(String.format(Constants.Messages.PARSING_LINE, lineId));

                    Line lineInformation = null;
                    if (fileCache.shouldUpdate(lineId, params.updateExisting)) {
                        lineInformation = crawler.parseLine(type, lineId);
                        if (lineInformation != null) {
                            fileCache.write(lineInformation);
                            logger.info("Line parsed ...");
                        } else {
                            logger.error(String.format(Constants.Messages.FAILED_TO_PARSE_LINE, lineId));
                        }
                    } else if (params.useCache) {
                        logger.info(Constants.Messages.LINE_EXISTS_GOT_CACHE);
                        lineInformation = fileCache.read(lineId);
                    } else {
                        logger.info(Constants.Messages.LINE_EXISTS_NO_INFO);
                    }

                    if (dataFixer != null && lineInformation != null) {
                        dataFixer.fixLine(lineInformation, agency.getCity());
                        fileCache.write(lineInformation);
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.info(String.format(Constants.Messages.END_JOB, Constants.JobNames.RATB));
    }
}
