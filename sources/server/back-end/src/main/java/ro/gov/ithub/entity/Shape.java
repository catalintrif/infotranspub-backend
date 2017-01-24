package ro.gov.ithub.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import ro.gov.ithub.base.BaseEntity;

import javax.persistence.*;
import java.util.List;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@Entity
@Table
public class Shape implements BaseEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Access(AccessType.PROPERTY)
    private Integer shapeId;

    @Column(nullable = false)
    private Double shapePtLat;

    @Column(nullable = false)
    private Double shapePtLon;

    @Column(nullable = false)
    private Double shapePtSequence;

    @Column
    private Double shapeDistTraveled;

    @ManyToMany
    private List<Trip> trips;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
