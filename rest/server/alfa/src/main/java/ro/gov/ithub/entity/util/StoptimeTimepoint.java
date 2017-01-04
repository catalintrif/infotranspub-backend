package ro.gov.ithub.entity.util;

/**
 * Created by Mihnea on 12/1/16.
 */
public enum StoptimeTimepoint {

    APPROXIMATE(0),
    EXACT(1);

    private Integer timepoint;

    StoptimeTimepoint(Integer timepoint) { this.timepoint = timepoint; }
}
