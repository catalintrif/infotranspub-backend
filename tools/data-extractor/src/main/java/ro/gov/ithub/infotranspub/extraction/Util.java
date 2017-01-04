package ro.gov.ithub.infotranspub.extraction;

public class Util {
    public static Double parseDouble(String some) {
        if (some == null || some.isEmpty()) {
            return null;
        }

        try {
            return Double.parseDouble(some);
        } catch (Exception e) {
            return null;
        }
    }
}
