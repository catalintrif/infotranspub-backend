package ro.gov.ithub.entity.util;

import lombok.Getter;

/**
 * Created by Mihnea on 11/26/16.
 */
public enum TripDirection {

    OUTBOUND(0),
    INBOUND(1);

    @Getter
    private int direction;

    TripDirection(int tripDirection) {
        this.direction = tripDirection;
    }
}
