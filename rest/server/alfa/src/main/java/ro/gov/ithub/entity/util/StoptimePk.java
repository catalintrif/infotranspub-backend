package ro.gov.ithub.entity.util;

import lombok.EqualsAndHashCode;
import ro.gov.ithub.entity.Stoptime;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Mihnea on 11/26/16.
 */
@EqualsAndHashCode
@Embeddable
@Access(AccessType.FIELD)
public class StoptimePk implements Serializable {

    static final String COLUMN_TRIP_ID = "TRIP_ID";
    static final String COLUMN_STOP_ID = "STOP_ID";

    @Column(name = StoptimePk.COLUMN_STOP_ID, nullable = false, unique = true)
    private Integer stopId;

    @Column(name = StoptimePk.COLUMN_TRIP_ID, nullable = false)
    private Integer tripId;
}
