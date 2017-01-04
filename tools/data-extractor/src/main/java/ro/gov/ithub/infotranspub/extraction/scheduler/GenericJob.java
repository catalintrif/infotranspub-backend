package ro.gov.ithub.infotranspub.extraction.scheduler;

import ro.gov.ithub.infotranspub.extraction.config.Configuration;
import ro.gov.ithub.infotranspub.extraction.extractor.GenericCrawler;
import ro.gov.ithub.infotranspub.extraction.gps.GpsDataFixer;

import java.io.File;

/**
 * Created by vlad4800@gmail.com on 13-Dec-16.
 *
 * Generic scheduled jobs class
 */
public abstract class GenericJob implements org.quartz.Job {

    protected final GenericCrawler crawler = Configuration.getCrawler();
    protected final GpsDataFixer dataFixer = Configuration.getDataFixer();
    protected final File cacheFolder = Configuration.getCacheFolder();
    protected final Configuration.RunParameters params = Configuration.getParams();
}
