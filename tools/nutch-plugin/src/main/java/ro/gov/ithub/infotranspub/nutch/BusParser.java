package ro.gov.ithub.infotranspub.nutch;

import org.apache.hadoop.conf.Configuration;
import org.apache.nutch.parse.ParseResult;
import org.apache.nutch.parse.Parser;
import org.apache.nutch.protocol.Content;

/**
 * Created by trifcat on 23.12.2016.
 */
public class BusParser implements Parser {

    private Configuration conf;

    public ParseResult getParse(Content c) {
        System.out.println("getParse() **********************************************");
        System.out.println(c.getUrl());

        ParseResult result = new ParseResult(c.getUrl());
        return result;
    }

    public void setConf(Configuration configuration) {
        this.conf = configuration;
    }

    public Configuration getConf() {
        return conf;
    }
}
