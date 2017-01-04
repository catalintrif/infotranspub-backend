package ro.gov.ithub.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import ro.gov.ithub.base.BaseEntity;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table
public class FareRule implements BaseEntity {

    static final String TABLE_NAME = "FARE_RULE";
    static final String COLUMN_ORIGIN_ID = "ORIGIN_ID";
    static final String COLUMN_DESTINATION_ID = "DESTINATION_ID";
    static final String COLUMN_CONTAINS_ID = "CONTAINS_ID";

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Access(AccessType.PROPERTY)
    private Integer fareId;

    @ManyToOne
    private Route route;

    @ManyToOne
    @JoinColumn(name = FareRule.COLUMN_ORIGIN_ID)
    private Stop origin;

    @ManyToOne
    @JoinColumn(name = FareRule.COLUMN_DESTINATION_ID)
    private Stop destination;

    @ManyToOne
    @JoinColumn(name = FareRule.COLUMN_CONTAINS_ID)
    private Stop contains;

    @OneToMany
    private List<FareAttribute> fareAttributes;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
