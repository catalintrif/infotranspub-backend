package ro.gov.ithub.entity.util;

import lombok.Getter;

/**
 * Created by Mihnea on 11/26/16.
 */
public enum TripBikesAllowed {

    NO_INFO(0),
    CAN_ACCOMODATE(1),
    NOT_ALLOWED(2);

    @Getter
    private int allowed;

    TripBikesAllowed(int bikesAllowed) {
        this.allowed = bikesAllowed;
    }
}
