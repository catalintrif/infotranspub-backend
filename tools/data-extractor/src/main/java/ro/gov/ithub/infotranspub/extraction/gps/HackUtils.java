package ro.gov.ithub.infotranspub.extraction.gps;

import java.util.HashMap;
import java.util.Map;

public class HackUtils {
    private static final Map<String, String> shortForms = new HashMap<>();

    static {
        addShortForm("sos.", "soseaua");
        addShortForm("cal.", "calea");
        addShortForm("bd.", "bulevardul");
        addShortForm("str.", "strada");
        addShortForm("prel.", "prelungirea");
        addShortForm("spl.", "splaiul");
        addShortForm("th.", "theodor");
        addShortForm("i.c.", "i.c.");
    }

    public static String fixAddresses(String data) {
        String result = data.toLowerCase();
        for (Map.Entry<String, String> entry : shortForms.entrySet()) {
            result = result.replaceAll(entry.getKey(), entry.getValue());
        }
        return result;
    }

    private static void addShortForm(String shortForm, String longForm) {
        if (shortForm.contains(".")) {
            shortForm = shortForm.replaceAll("[.]", "[.]");
        }
        shortForm = shortForm.toLowerCase().trim();
        longForm = longForm.toLowerCase().trim() + " ";
        shortForms.put(shortForm, longForm);
    }
}
