package ro.gov.ithub.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import ro.gov.ithub.base.BaseEntity;
import ro.gov.ithub.entity.util.RouteType;

import javax.persistence.*;
import java.util.Set;

@Data
@NoArgsConstructor
@Entity
@Table
public class Route implements BaseEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Access(AccessType.PROPERTY)
    private Integer routeId;

    @ManyToOne
    private Agency agency;

    @Column(nullable = false)
    private String routeShortName;

    @Column(nullable = false)
    private String routeLongName;

    @Column
    private String routeDesc;

    @Enumerated
    private RouteType routeType;

    @Column
    private String routeUrl;

    @Column
    private String routeColor;

    @Column
    private String routeTextColor;

    //    TODO ensure correctness
    @OneToMany
    private Set<FareRule> fareRules;

    //    TODO ensure correctness
    @OneToMany
    private Set<Trip> trips;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
