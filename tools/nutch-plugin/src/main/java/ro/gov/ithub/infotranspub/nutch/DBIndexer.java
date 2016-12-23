package ro.gov.ithub.infotranspub.nutch;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapred.JobConf;
import org.apache.nutch.indexer.IndexWriter;
import org.apache.nutch.indexer.IndexerMapReduce;
import org.apache.nutch.indexer.NutchDocument;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * Created by trifcat on 23.12.2016.
 */
public class DBIndexer implements IndexWriter {
    private Configuration config;
    private Writer writer;
    private boolean delete = false;

    public void open(JobConf job, String name) throws IOException {
        delete = job.getBoolean(IndexerMapReduce.INDEXER_DELETE, false);
    }

    public void delete(String key) throws IOException {
        if (delete) {
            writer.write("delete\t" + key + "\n");
        }
    }

    public void update(NutchDocument doc) throws IOException {
        writer.write("update " + doc.getFieldValue("digest") + " " + doc.getFieldValue("id") + "\n");
    }

    public void write(NutchDocument doc) throws IOException {
        writer.write("add " + doc.getFieldValue("digest") + " " + doc.getFieldValue("id") + "\n");
    }

    public void close() throws IOException {
        writer.flush();
        writer.close();
    }

    public void commit() throws IOException {
        writer.write("commit\n");
    }

     public Configuration getConf() {
        return config;
    }

    public void setConf(Configuration conf) {
        config = conf;
        String path = conf.get("dummy.path");
        if (path == null) {
            String message = "Missing path. Should be set via -Ddummy.path";
            message += "\n" + describe();
            throw new RuntimeException(message);
        }

        try {
            writer = new BufferedWriter(new FileWriter(conf.get("dummy.path")));
        } catch (IOException e) {
        }
    }

    public String describe() {
        StringBuffer sb = new StringBuffer("DBIndexer\n");
        sb.append("\t").append(
                "dummy.path : Path of the file to write to (mandatory)\n");
        return sb.toString();
    }
}
