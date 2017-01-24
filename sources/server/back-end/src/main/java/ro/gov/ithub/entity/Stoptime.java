package ro.gov.ithub.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import ro.gov.ithub.base.BaseEntity;
import ro.gov.ithub.entity.util.StoptimeBoardingType;
import ro.gov.ithub.entity.util.StoptimePk;
import ro.gov.ithub.entity.util.StoptimeTimepoint;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.Date;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@Entity
@Table(name = Stoptime.TABLE_NAME,
        uniqueConstraints =
        @UniqueConstraint(columnNames = {
                Stoptime.COLUMN_STOP_ID,
                Stoptime.COLUMN_TRIP_ID})
)
public class Stoptime implements BaseEntity {

    static final String TABLE_NAME = "STOPTIME";
    static final String COLUMN_TRIP_ID = "TRIP_ID";
    static final String COLUMN_STOP_ID = "STOP_ID";
    static final String FIELD_TRIP_ID = "tripId";
    static final String FIELD_STOP_ID = "stopId";

    @EmbeddedId
    private StoptimePk stoptimePk;

    @Column(nullable = false)
    private Date arrivalTime;

    @Column(nullable = false)
    private Date departureTime;

    @Min(value = 1)
    @Column(nullable = false)
    private Integer stopSequence;

    @Column
    private String stopHeadsign;

    @Enumerated
    private StoptimeBoardingType pickupType = StoptimeBoardingType.NONE_AVAILABLE;

    @Enumerated
    private StoptimeBoardingType dropOffType = StoptimeBoardingType.NONE_AVAILABLE;

    @Column
    private Double shapeDistTraveled;

    @Column
    private StoptimeTimepoint timepoint = StoptimeTimepoint.EXACT;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
