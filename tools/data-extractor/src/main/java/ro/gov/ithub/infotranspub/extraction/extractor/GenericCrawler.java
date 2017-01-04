package ro.gov.ithub.infotranspub.extraction.extractor;

import ro.gov.ithub.infotranspub.extraction.data.Agency;
import ro.gov.ithub.infotranspub.extraction.data.Line;
import ro.gov.ithub.infotranspub.extraction.data.LineType;

import java.io.IOException;
import java.util.List;

public abstract class GenericCrawler {
    public abstract Agency getAgency();

    public abstract List<String> parseLinesIds(LineType type) throws IOException;

    public abstract Line parseLine(LineType type, String lineId) throws IOException;
}
