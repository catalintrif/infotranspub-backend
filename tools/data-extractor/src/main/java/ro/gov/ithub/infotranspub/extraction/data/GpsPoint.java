package ro.gov.ithub.infotranspub.extraction.data;

public class GpsPoint {
    private Double latitude;
    private Double longitude;

    public GpsPoint(Double latitude, Double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GpsPoint gpsPoint = (GpsPoint) o;

        return (latitude != null ? latitude.equals(gpsPoint.latitude) : gpsPoint.latitude == null)
                && (longitude != null ? longitude.equals(gpsPoint.longitude) : gpsPoint.longitude == null);
    }

    @Override
    public int hashCode() {
        int result = latitude != null ? latitude.hashCode() : 0;
        result = 31 * result + (longitude != null ? longitude.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GpsPoint{" +
                "latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }
}
