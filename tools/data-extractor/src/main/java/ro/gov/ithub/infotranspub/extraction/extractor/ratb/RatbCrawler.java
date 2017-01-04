package ro.gov.ithub.infotranspub.extraction.extractor.ratb;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import ro.gov.ithub.infotranspub.extraction.data.*;
import ro.gov.ithub.infotranspub.extraction.extractor.GenericCrawler;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static ro.gov.ithub.infotranspub.extraction.data.ScheduleType.none;
import static ro.gov.ithub.infotranspub.extraction.extractor.ratb.RatbUtil.*;

public class RatbCrawler extends GenericCrawler {
    private static final Logger logger = LogManager.getLogger(RatbCrawler.class);

    private static final Agency AGENCY = new Agency("RATB", new City("Bucuresti", "Romania"));

    private static final String BASE_URL = "http://ratb.ro/";
    private static final String NIGHT_URL_PATTERN = BASE_URL + "v_statii_noapte.php?tlin1=%s";

    private static final Map<LineType, String> urlMappings = new HashMap<>();

    static {
        urlMappings.put(LineType.tram, BASE_URL + "v_tramvai.php");
        urlMappings.put(LineType.bus, BASE_URL + "v_bus_urban.php");
        urlMappings.put(LineType.trolleyBus, BASE_URL + "v_troleibuz.php");
        urlMappings.put(LineType.express, BASE_URL + "v_bus_expres.php");
        urlMappings.put(LineType.metropolitan, BASE_URL + "v_bus_preorasenesc.php");
        urlMappings.put(LineType.night, BASE_URL + "v_noapte.php");
    }

    @Override
    public Agency getAgency() {
        return AGENCY;
    }

    public List<String> parseLinesIds(LineType type) throws IOException {
        String url = urlMappings.get(type);
        if (url == null) {
            logger.error("Invalid url for line type = " + type);
            return Collections.emptyList();
        }

        if (type == LineType.night) {
            return parseNightLineIds(url);
        } else {
            return parseDayLineIds(url);
        }
    }

    private List<String> parseNightLineIds(String url) throws IOException {
        List<String> result = new LinkedList<>();

        Document doc = Jsoup.connect(url).get();
        Elements lines = doc.select("table td[style=\"font-weight: bold\"] a");

        for (Element element : lines) {
            String value = element.text();
            result.add(value);
        }

        return result;
    }

    private List<String> parseDayLineIds(String url) throws IOException {
        List<String> result = new LinkedList<>();

        Document doc = Jsoup.connect(url).get();
        Elements lines = doc.select("select[name=tlin1] option");

        for (Element element : lines) {
            String value = element.attr("value");
            if (!"0".equals(value)) {
                result.add(value);
            }
        }

        return result;
    }

    public Line parseLine(LineType type, String lineId) throws IOException {
        Document doc = getLineDocument(type, lineId);
        if (doc == null) {
            return null;
        }

        Elements stops = doc.select("table[border=1][cellpadding=2] tr");

        Line result = new Line(type, lineId);

        LineDirection direction = LineDirection.up;

        for (Element stop : stops) {
            Elements td = stop.select("td");
            if (td.size() == 1) {
                direction = parseDirection(stop.select("td").get(0).text());
            } else if (td.size() == 4 && "left".equalsIgnoreCase(stop.attr("align"))) {
                // parse stop
                LineStop lineStop = new LineStop(td.get(0).text(), td.get(1).text(), td.get(3).text());
                parseStationMetadata(td.get(1).attr("title"), lineStop);
                if (lineStop.getGpsCoordinates() == null) {
                    logger.error("Unable to parse GPS coordinates for " + lineStop);
                }
                // parse schedule
                Elements links = td.get(0).select("a");
                if (!links.isEmpty()) {
                    String scheduleUrl = links.get(0).attr("href");
                    if (scheduleUrl != null && !scheduleUrl.isEmpty()) {
                        lineStop.setScheduleTable(parseSchedule(BASE_URL + scheduleUrl));
                    } else {
                        logger.error("Invalid schedule url for line type = " + type + " line id = " + lineId);
                    }
                }
                result.addStop(direction, lineStop);
            }
        }

        return result;
    }

    private Document getLineDocument(LineType type, String lineId) throws IOException {
        if (type == LineType.night) {
            return Jsoup.connect(String.format(NIGHT_URL_PATTERN, lineId)).get();
        } else {
            String url = urlMappings.get(type);
            if (url == null) {
                logger.error("Invalid url for line type = " + type);
                return null;
            }

            return Jsoup.connect(url).header("Content-Type", "application/x-www-form-urlencoded")
                        .data("tlin1", lineId).post();
        }
    }

    private Map<ScheduleType, List<String>> parseSchedule(String url) throws IOException {
        Map<ScheduleType, List<String>> result = new HashMap<>();
        Document doc = Jsoup.connect(url).get();
        Elements tables = doc.select("table[border=1][cellpadding=5]");
        for (Element table : tables) {
            Map.Entry<ScheduleType, List<String>> entry = parseScheduleTable(table);
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    private Map.Entry<ScheduleType, List<String>> parseScheduleTable(Element element) {
        Elements rows = element.select("tr");
        ScheduleType type = parseScheduleType(rows.get(0).text());

        if (type == none) {
            logger.error("Invalid schedule type for element = " + element);
            return new AbstractMap.SimpleImmutableEntry<>(type, Collections.emptyList());
        } else {
            List<String> header = rows.get(1).select("td").stream().map(Element::text).collect(Collectors.toList());
            header.remove(0);
            if (!header.isEmpty()) {
                List<String> values = rows.get(2).select("td").stream().map(Element::text).collect(Collectors.toList());
                values.remove(0);

                return new AbstractMap.SimpleImmutableEntry<>(type, parseTimetable(header, values));
            } else {
                return new AbstractMap.SimpleImmutableEntry<>(type, Collections.emptyList());
            }
        }
    }
}
