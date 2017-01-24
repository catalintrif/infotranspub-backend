package ro.gov.ithub.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import ro.gov.ithub.base.BaseEntity;

import javax.persistence.*;
import java.util.Set;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@Entity
@Table
public class City implements BaseEntity {

    static final String TABLE_NAME = "CITY";

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Access(AccessType.PROPERTY)
    private Integer cityId;

    @Column
    private String cityUrl;

    @Column(nullable = false, unique = true)
    private String cityShortName;

    @Column(nullable = false)
    private String cityName;

    @OneToMany
    @JoinColumn(name = Agency.COLUMN_CITY_ID)
    private Set<Agency> agencies;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

}


