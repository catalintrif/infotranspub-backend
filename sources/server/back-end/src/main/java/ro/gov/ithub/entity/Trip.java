package ro.gov.ithub.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import ro.gov.ithub.base.BaseEntity;
import ro.gov.ithub.entity.util.TripBikesAllowed;
import ro.gov.ithub.entity.util.TripDirection;
import ro.gov.ithub.entity.util.WheelchairAccessible;

import javax.persistence.*;
import java.util.Set;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@Entity
@Table
public class Trip implements BaseEntity {

    static final String TABLE_NAME = "TRIP";
    static final String COLUMN_SERVICE_ID = "SERVICE_ID";

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Access(AccessType.PROPERTY)
    private Integer tripId;

    @Column(nullable = false)
    private Route route;

    @Column(nullable = false)
    private Integer serviceId;

    @Column
    private String tripHeadsign;

    @Column
    private String tripShortname;

    @Enumerated
    private TripDirection directionId;

    @Column
    private Integer blockId;

    @Column
    private Integer shapeId;

    @ManyToMany
    private Set<Shape> shapes;

    @OneToOne(optional = false)
    private Calendar calendar;

    @OneToMany
    private Set<Frequency> frequencies;

    @Enumerated
    private WheelchairAccessible wheelchairAccessible;

    @Enumerated
    private TripBikesAllowed bikesAllowed;

    @OneToMany
    @JoinTable(name = Stoptime.TABLE_NAME,
            joinColumns = { @JoinColumn(name = Stoptime.COLUMN_TRIP_ID) },
            inverseJoinColumns = { @JoinColumn(name = Stoptime.COLUMN_STOP_ID) } )
    private Set<Stop> stops;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
