package ro.gov.ithub.infotranspub.tools.transcoder.metrorex.temp;
import java.util.ArrayList;

public class TempTextFile {

    public static final String WRITE="w";
    public static final String READ="r";
    public static final String APPEND="a";

    public static final int FIRST_LINE = 0;


    private ArrayList<String> lines = null;
    private int currentLine = FIRST_LINE;
    boolean openFlag = false;

    public TempTextFile() {
        lines = new ArrayList<String>();
    }

    public boolean open(String mode){
        if ( mode.equals(WRITE)){
            lines = new ArrayList<String>();
            int currentLine = FIRST_LINE;
            openFlag = true;
        } else if ( mode.equals(READ)){
            int currentLine = FIRST_LINE;
            openFlag = true;

        } else if ( mode.equals(APPEND)){
            int currentLine = lines.size();
            openFlag = true;

        } else {
            openFlag = false;
        }
        return openFlag;
    }

    public boolean close(){
        if ( openFlag){
            currentLine = -1;
            openFlag = false;
            return true;
        }
        currentLine = -1;
        return false;
    }


    public boolean write(String line){
        if (openFlag){
            lines.add(line);
            currentLine ++;
        }
        return openFlag;
    }

}
