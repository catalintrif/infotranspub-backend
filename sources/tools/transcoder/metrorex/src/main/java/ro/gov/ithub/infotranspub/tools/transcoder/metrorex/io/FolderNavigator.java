package ro.gov.ithub.infotranspub.tools.transcoder.metrorex.io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public class FolderNavigator {
    private String currentFolder = null;

    {
        currentFolder = System.getProperty("user.home");
    }


    public FolderNavigator() {

    }
    public FolderNavigator(String initialFolder) {
        changeCurrentFolder(initialFolder);

    }

    public Boolean folderExists(String folderPath){
        if ( folderPath != null ) {
            File f = new File(folderPath);
            if (f.exists() && f.isDirectory()) {
                return Boolean.TRUE;
            }

        }
        return Boolean.FALSE;
    }

    public Boolean changeCurrentFolder(String folderPath){
        if ( folderExists(folderPath)){
            currentFolder = folderPath;
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    List<String> scanCurrentFolder(){
        if (folderExists(currentFolder)){
            ArrayList<String> result = new ArrayList<String>();

            File f = null;
            File[] paths;

            try{
                // create new file
                f = new File(currentFolder);

                // returns pathnames for files and directory
                paths = f.listFiles();

                // for each pathname in pathname array
                for(File path:paths)
                {
                    // prints file and directory paths
                    //System.out.println(path);
                    result.add(path.toString());
                }
                if (result.size() == 0)
                    result = null;

                return result;
            }catch(Exception e){
                // if any error occurs
                e.printStackTrace();
            }
        }
        return null;
    }

    List<String> scanCurrentFolderWithFilter(FilenameFilter filter){
        if (folderExists(currentFolder)){
            ArrayList<String> result = new ArrayList<String>();

            File f = null;
            File[] paths;

            try{
                // create new file
                f = new File(currentFolder);

                if (filter != null)
                    paths =  f.listFiles(filter);
                else
                    paths = f.listFiles();

                // for each pathname in pathname array
                for(File path:paths)
                {
                    // prints file and directory paths
                    System.out.println(path);
                    result.add(path.toString());
                }
                if (result.size() == 0)
                    result = null;
                return result;
            }catch(Exception e){
                // if any error occurs
                e.printStackTrace();
            }
        }
        return null;
    }

}
