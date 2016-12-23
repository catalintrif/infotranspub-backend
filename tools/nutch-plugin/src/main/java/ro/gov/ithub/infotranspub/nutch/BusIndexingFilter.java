package ro.gov.ithub.infotranspub.nutch;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.Text;
import org.apache.nutch.crawl.CrawlDatum;
import org.apache.nutch.crawl.Inlinks;
import org.apache.nutch.indexer.IndexingException;
import org.apache.nutch.indexer.IndexingFilter;
import org.apache.nutch.indexer.IndexingFiltersChecker;
import org.apache.nutch.indexer.NutchDocument;
import org.apache.nutch.parse.Parse;

/**
 * Created by trifcat on 22.12.2016.
 */
public class BusIndexingFilter implements IndexingFilter {

    public static void main(String[] arg) throws Exception {
        IndexingFiltersChecker checker = new IndexingFiltersChecker();
        checker.run(new String[] {"x"});
    }

    private Configuration conf;

    public NutchDocument filter(NutchDocument nutchDocument, Parse parse, Text text, CrawlDatum crawlDatum, Inlinks inlinks) throws IndexingException {
        System.out.println("filter()");
        nutchDocument.add("bubu", "mic");
        for (String fieldName : nutchDocument.getFieldNames()) {
            System.out.println(fieldName + "=" + nutchDocument.getField(fieldName));
        }
        nutchDocument.getDocumentMeta().add("meta", "data");
        for (String metadata : nutchDocument.getDocumentMeta().names()) {
            System.out.println("Metadata " + metadata + "="
                    + nutchDocument.getField(nutchDocument.getDocumentMeta().get(metadata)));
        }
        return nutchDocument;
    }

    public void setConf(Configuration configuration) {
        System.out.println("setConf()");
        this.conf = configuration;
    }

    public Configuration getConf() {
        System.out.println("getConf()");
        return conf;
    }
}
