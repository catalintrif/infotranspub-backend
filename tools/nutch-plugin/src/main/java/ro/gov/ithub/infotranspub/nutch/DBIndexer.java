package ro.gov.ithub.infotranspub.nutch;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapred.JobConf;
import org.apache.nutch.indexer.IndexWriter;
import org.apache.nutch.indexer.IndexerMapReduce;
import org.apache.nutch.indexer.NutchDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

/**
 * Plugin pentru crawler-ul Apache Nutch (http://nutch.apache.org) care scrie intr-o tabela
 * URL-urile nou gasite sau modificate.
 * @author Catalin Trif
 */
public class DBIndexer implements IndexWriter {
    private static Logger LOG = LoggerFactory.getLogger(DBIndexer.class);
    private static String SQL_SELECT = "SELECT hash FROM @table WHERE url = ?";
    private static String SQL_INSERT = "INSERT INTO @table(url, hash, processed, modified_date) VALUES (?, ?, ?, ?)";
    private static String SQL_UPDATE = "UPDATE @table SET hash = ?, processed = ?, modified_date = ? WHERE url = ?";
    private Configuration config;
    private Connection connection;
    private boolean delete = false;
    private PreparedStatement select;
    private PreparedStatement insert;
    private PreparedStatement update;


    public void open(JobConf job, String name) throws IOException {
        delete = job.getBoolean(IndexerMapReduce.INDEXER_DELETE, false);
    }

    public void delete(String key) throws IOException {
        if (delete) {
            String message = "Should delete: " + key;
            LOG.info(message);
            System.out.println(message);
        }
    }

    public void update(NutchDocument doc) throws IOException {
        write(doc);
    }

    public void write(NutchDocument doc) throws IOException {
        String url = doc.getFieldValue("id").toString();
        String hash = doc.getFieldValue("digest").toString();
        try {
            select.setString(1, url);
            ResultSet rs = select.executeQuery();
            if (rs.next()) {
                LOG.info("Found in DB: " + url);
                String dbHash = rs.getString(1);
                if (!hash.equals(dbHash)) {
                    databaseUpdate(url, hash);
                }
            } else {
                databaseInsert(url, hash);
            }
            rs.close();
        } catch (SQLException e) {
            throw new IOException(e.getMessage());
        }
    }

    private void databaseUpdate(String url, String hash) throws SQLException {
        String message = "Updating in DB: " + url;
        System.out.println(message);
        LOG.info(message);
        update.setString(1, hash);
        update.setBoolean(2, false);
        update.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
        update.setString(4, url);
        update.executeUpdate();
    }

    private void databaseInsert(String url, String hash) throws SQLException {
        String message = "Inserting in DB: " + url;
        System.out.println(message);
        LOG.info(message);
        insert.setString(1, url);
        insert.setString(2, hash);
        insert.setBoolean(3, false);
        insert.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
        insert.executeUpdate();
    }

    public void close() throws IOException {
        try {
            select.close();
            update.close();
            insert.close();
            connection.close();
        } catch (SQLException e) {
            throw new IOException(e.getMessage());
        }
    }

    public void commit() throws IOException {
        LOG.debug("commit()");
    }

    public Configuration getConf() {
        return config;
    }

    public void setConf(Configuration conf) {
        config = conf;
        try {
            Class.forName("org.postgresql.Driver");
            InputStream is = getConf().getConfResourceAsInputStream("indexer-db.properties");
            Properties properties = new Properties();
            properties.load(is);
            String url = properties.getProperty("jdbc.url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            String table = properties.getProperty("table");

            connection = DriverManager.getConnection(url, user, password);
            LOG.info("Connected to: " + url);
            select = connection.prepareStatement(SQL_SELECT.replaceFirst("@table", table));
            insert = connection.prepareStatement(SQL_INSERT.replaceFirst("@table", table));
            update = connection.prepareStatement(SQL_UPDATE.replaceFirst("@table", table));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public String describe() {
        return "DBIndexer\n\tConfiguration file: $NUTCH_HOME/conf/indexer-db.properties\n";
    }

}
