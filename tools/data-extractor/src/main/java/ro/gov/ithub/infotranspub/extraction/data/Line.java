package ro.gov.ithub.infotranspub.extraction.data;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Line {
    private LineType type;
    private String lineId;
    private Map<LineDirection, List<LineStop>> lineStops = new HashMap<>();

    public Line(LineType type, String lineId) {
        this.type = type;
        this.lineId = lineId;
    }

    public LineType getType() {
        return type;
    }

    public String getLineId() {
        return lineId;
    }

    public Map<LineDirection, List<LineStop>> getLineStops() {
        return lineStops;
    }

    public List<LineStop> getLineStops(LineDirection direction) {
        return lineStops.get(direction);
    }

    public void addStop(LineDirection direction, LineStop stop) {
        List<LineStop> stops = lineStops.computeIfAbsent(direction, k -> new LinkedList<>());
        stops.add(stop);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Line line = (Line) o;

        return type == line.type && (lineId != null ? lineId.equals(line.lineId) : line.lineId == null);
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (lineId != null ? lineId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Line{" +
                "type=" + type +
                ", lineId='" + lineId + '\'' +
                ", lineStops=" + lineStops +
                '}';
    }
}
