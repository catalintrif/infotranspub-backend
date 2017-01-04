package ro.gov.ithub.infotranspub.gtfs;
import java.lang.String;


public class ShapesFields implements GTFSFields {
    public static final String FIELD_SHAPE_ID = "shape_id";
    public static final String FIELD_SHAPE_PT_LAT = "shape_pt_lat";
    public static final String FIELD_SHAPE_PT_LON = "shape_pt_lon";
    public static final String FIELD_SHAPE_PT_SEQUENCE = "shape_pt_sequence";
    public static final String FIELD_SHAPE_DIST_TRAVELED = "shape_dist_traveled";

    public ShapesFields() {
    }
    
    public String[] getFieldList() {
        String [] fields =  
                {     
                    ShapesFields.FIELD_SHAPE_ID, 
                    ShapesFields.FIELD_SHAPE_PT_LAT, 
                    ShapesFields.FIELD_SHAPE_PT_LON, 
                    ShapesFields.FIELD_SHAPE_PT_SEQUENCE, 
                    ShapesFields.FIELD_SHAPE_DIST_TRAVELED
                };
        return fields;
    }
    
}


