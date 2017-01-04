package ro.gov.ithub.infotranspub.extraction.data;

import java.util.List;
import java.util.Map;

public class LineStop {
    private String stopId;
    private String stopCode;
    private String name;
    private String location;
    private GpsPoint gpsCoordinates;
    private Map<ScheduleType, List<String>> scheduleTable;

    public LineStop(String stopId, String name, String location) {
        this.stopId = stopId;
        this.name = name;
        this.location = location;
    }

    public String getStopId() {
        return stopId;
    }

    public String getStopCode() {
        return stopCode;
    }

    public String getName() {
        return name;
    }

    public void setStopCode(String stopCode) {
        this.stopCode = stopCode;
    }

    public void setGpsCoordinates(GpsPoint gpsCoordinates) {
        this.gpsCoordinates = gpsCoordinates;
    }

    public String getLocation() {
        return location;
    }

    public GpsPoint getGpsCoordinates() {
        return gpsCoordinates;
    }

    public Map<ScheduleType, List<String>> getScheduleTable() {
        return scheduleTable;
    }

    public void setScheduleTable(Map<ScheduleType, List<String>> scheduleTable) {
        this.scheduleTable = scheduleTable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LineStop lineStop = (LineStop) o;

        return stopId.equals(lineStop.stopId);
    }

    @Override
    public int hashCode() {
        return stopId.hashCode();
    }

    @Override
    public String toString() {
        return "LineStop{" +
                "stopId='" + stopId + '\'' +
                ", stopCode='" + stopCode + '\'' +
                ", name='" + name + '\'' +
                ", location='" + location + '\'' +
                ", gpsCoordinates=" + gpsCoordinates +
                ", scheduleTable=" + scheduleTable +
                '}';
    }
}
