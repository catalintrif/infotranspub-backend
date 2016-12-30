package ro.gov.ithub.infotranspub.tools.transcoder.metrorex.io;

import java.io.File;
import java.io.FilenameFilter;

public class FolderFilter implements FilenameFilter{
    @Override
    public boolean accept(File pathname, String name) {
        if ( pathname.isDirectory() ){
            return true;
        }
        return false;
    }

}

