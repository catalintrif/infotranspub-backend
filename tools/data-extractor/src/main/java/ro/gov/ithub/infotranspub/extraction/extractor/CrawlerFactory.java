package ro.gov.ithub.infotranspub.extraction.extractor;

import ro.gov.ithub.infotranspub.extraction.extractor.ratb.RatbCrawler;

public class CrawlerFactory {
    public static GenericCrawler createCrawler(String name) {
        switch (name) {
            case "ratb":
                return new RatbCrawler();
            default:
                throw new IllegalArgumentException("Invalid crawler name");
        }
    }
}
