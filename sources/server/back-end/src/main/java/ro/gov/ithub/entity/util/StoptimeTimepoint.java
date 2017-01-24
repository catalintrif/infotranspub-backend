package ro.gov.ithub.entity.util;

import lombok.Getter;

/**
 * Created by Mihnea on 12/1/16.
 */
public enum StoptimeTimepoint {

    APPROXIMATE(0),
    EXACT(1);

    @Getter
    private Integer timepoint;

    StoptimeTimepoint(Integer stoptimeTimepoint) { this.timepoint = stoptimeTimepoint; }
}
