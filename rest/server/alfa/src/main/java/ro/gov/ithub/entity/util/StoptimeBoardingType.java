package ro.gov.ithub.entity.util;

/**
 * Created by Mihnea on 12/1/16.
 */
public enum StoptimeBoardingType {

    REGULARLY_SCHEDULED(0),
    NONE_AVAILABLE(1),
    PHONE_AGENCY_TO_ARRANGE(2),
    COORDINATE_WITH_DRIVER_TO_ARRANGE(3);

    private Integer pickupType;

    StoptimeBoardingType(int type) { this.pickupType = type; }
}
