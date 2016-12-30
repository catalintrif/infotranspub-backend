package ro.gov.ithub.infotranspub.tools.transcoder.metrorex.io;


import java.io.File;
import java.io.FilenameFilter;

public class SkipFilter implements FilenameFilter{
    @Override
    public boolean accept(File pathname, String name) {
        return false;
    }
}
