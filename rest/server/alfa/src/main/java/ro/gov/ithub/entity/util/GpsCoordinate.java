package ro.gov.ithub.entity.util;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class GpsCoordinate {

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;
}
