package ro.gov.ithub.infotranspub.extraction.output;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ro.gov.ithub.infotranspub.extraction.data.Line;

import java.io.*;

public class JsonFileCache {
    private String filenamePattern;
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public JsonFileCache(String filenamePattern) {
        this.filenamePattern = filenamePattern;
    }

    public boolean write(Line line) {
        File filename = new File(String.format(filenamePattern, line.getLineId()));
        try (PrintWriter pw = new PrintWriter(filename)) {
            pw.print(gson.toJson(line));
            return true;
        } catch (FileNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public Line read(String lineId) {
        File filename = new File(String.format(filenamePattern, lineId));
        try (FileReader br = new FileReader(filename)) {
            return gson.fromJson(br, Line.class);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public boolean shouldUpdate(String lineId, boolean updateExisting) {
        File filename = new File(String.format(filenamePattern, lineId));
        return !filename.exists() || updateExisting;
    }
}
