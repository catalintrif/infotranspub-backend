package ro.gov.ithub.infotranspub.tools.transcoder.metrorex.io;

import java.io.File;
import java.io.FilenameFilter;

public class ExcelFileFilter implements FilenameFilter{

    @Override
    public boolean accept(File pathname, String name) {
        String fullPath = pathname + "/" + name;
        File f = new File(fullPath);
        if ( f.isFile() &&  (name.endsWith(".xlsx") || (name.endsWith(".xls")))){
            return true;
        }
        return false;
    }
}
