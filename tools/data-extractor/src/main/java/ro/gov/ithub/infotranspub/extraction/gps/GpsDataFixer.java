package ro.gov.ithub.infotranspub.extraction.gps;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.dudie.nominatim.client.JsonNominatimClient;
import fr.dudie.nominatim.model.Address;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.SingleClientConnManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ro.gov.ithub.infotranspub.extraction.data.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static ro.gov.ithub.infotranspub.extraction.gps.HackUtils.fixAddresses;


public class GpsDataFixer {
    private static final Logger logger = LogManager.getLogger(GpsDataFixer.class);

    private static final Map<LineType, String> typeMappings = new HashMap<>();

    static {
        typeMappings.put(LineType.tram, "tram_stop");
        typeMappings.put(LineType.bus, "bus_stop");
        typeMappings.put(LineType.trolleyBus, "bus_stop");
        typeMappings.put(LineType.express, "bus_stop");
        typeMappings.put(LineType.metropolitan, "bus_stop");
        typeMappings.put(LineType.night, "bus_stop");
    }

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private JsonNominatimClient nominatimClient;
    private Map<String, GpsPoint> pointCache = new HashMap<>();

    public GpsDataFixer() {
        final SchemeRegistry registry = new SchemeRegistry();
        registry.register(new Scheme("http", new PlainSocketFactory(), 80));
        final ClientConnectionManager connexionManager = new SingleClientConnManager(null, registry);

        final HttpClient httpClient = new DefaultHttpClient(connexionManager, null);

        nominatimClient = new JsonNominatimClient(httpClient, "abc@bac.com");
    }

    public void fixLine(Line line, City agencyLocation) {
        if (line == null) {
            return;
        }

        for (LineDirection direction : LineDirection.values()) {
            List<LineStop> stops = line.getLineStops(direction);
            if (stops != null) {
                for (LineStop stop : stops) {
                    if (stop.getGpsCoordinates() == null) {
                        String poi = stop.getName() + ", " + fixAddresses(stop.getLocation()) + ", " +
                                agencyLocation.getName() + ", " + agencyLocation.getCountry();
                        GpsPoint point = findGpsPoint(poi, line.getType());
                        stop.setGpsCoordinates(point);
                    }
                }
            }
        }

    }

    private GpsPoint findGpsPoint(String poi, LineType lineType) {
        String stopType = typeMappings.get(lineType);
        if (stopType == null) {
            logger.error("Invalid line type = " + lineType + " for POI = " + poi);
            return null;
        }

        String cacheKey = poi + "~" + stopType;

        if (pointCache.containsKey(cacheKey)) {
            return pointCache.get(cacheKey);
        }

        try {
            List<Address> addresses = nominatimClient.search(poi);
            if (addresses.isEmpty()) {
                logger.error("Could not find POI for " + poi + " Line type = " + lineType + " Address list is empty");
                return null;
            } else {
                for (Address address : addresses) {
                    if (address.getElementType().equalsIgnoreCase(stopType)) {
                        GpsPoint point = new GpsPoint(address.getLatitude(), address.getLongitude());
                        pointCache.put(cacheKey, point);
                        return point;
                    }
                }

                logger.error("Could not find POI for " + poi + " Line type = " + lineType);
                logger.debug(gson.toJson(addresses));
                pointCache.put(cacheKey, null);
                return null;
            }
        } catch (IOException e) {
            logger.error("Could not retrieve GPS coordinate. Cause: " + e.getMessage());
            return null;
        }
    }
}
