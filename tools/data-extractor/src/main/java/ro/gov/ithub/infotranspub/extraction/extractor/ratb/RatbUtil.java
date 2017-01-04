package ro.gov.ithub.infotranspub.extraction.extractor.ratb;

import com.google.common.base.Splitter;
import ro.gov.ithub.infotranspub.extraction.data.GpsPoint;
import ro.gov.ithub.infotranspub.extraction.data.LineDirection;
import ro.gov.ithub.infotranspub.extraction.data.LineStop;
import ro.gov.ithub.infotranspub.extraction.data.ScheduleType;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import static ro.gov.ithub.infotranspub.extraction.Util.parseDouble;

class RatbUtil {
    static LineDirection parseDirection(String text) {
        if ("TUR".equalsIgnoreCase(text)) {
            return LineDirection.up;
        } else {
            return LineDirection.down;
        }
    }

    static ScheduleType parseScheduleType(String text) {
        if ("LUCRU".equalsIgnoreCase(text)) {
            return ScheduleType.weekdays;
        } else if ("SAMBATA".equalsIgnoreCase(text)) {
            return ScheduleType.saturday;
        } else if ("DUMINICA".equalsIgnoreCase(text)) {
            return ScheduleType.sunday;
        } else {
            return ScheduleType.none;
        }
    }

    static void parseStationMetadata(String metadata, LineStop lineStop) {
        if (metadata == null || metadata.trim().isEmpty()) {
            return;
        }

        Map<String, String> prop = Splitter.on("\n").omitEmptyStrings().trimResults()
                                           .withKeyValueSeparator("=")
                                           .split(metadata.trim().toLowerCase());
        Double lt = null;
        Double lg = null;

        // the title/header has some weird whitespace chars into it, so this is a possible solution
        // can be removed if the properties are parsed manually
        for (Map.Entry<String, String> e : prop.entrySet()) {
            if (e.getKey().contains("cod")) {
                lineStop.setStopCode(e.getValue().trim());
            } else if (e.getKey().contains("lat")) {
                lt = parseDouble(e.getValue().trim());
            } else if (e.getKey().contains("long")) {
                lg = parseDouble(e.getValue().trim());
            }
        }
        if (lg != null && lt != null) {
            lineStop.setGpsCoordinates(new GpsPoint(lt, lg));
        } else {
            lineStop.setGpsCoordinates(null);
        }
    }

    static List<String> parseTimetable(List<String> header, List<String> values) {
        List<String> result = new LinkedList<>();

        for (int i = 0; i < header.size(); i++) {
            String value = values.get(i);
            String hour = header.get(i).trim();
            if (value != null) {
                value = value.trim();
                if (!value.isEmpty()) {
                    String[] minutes = value.split(" ");
                    if (minutes.length > 0) {
                        for (String minute : minutes) {
                            minute = minute.trim();
                            if (!minute.isEmpty()) {
                                if (minute.length() < 2) {
                                    minute = "0" + minute;
                                }
                                result.add(hour + ":" + minute);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
}
