package ro.gov.ithub.entity.util;

import lombok.EqualsAndHashCode;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Mihnea on 11/26/16.
 */
@EqualsAndHashCode
@Embeddable
@Access(AccessType.FIELD)
public class CalendarDatesPk implements Serializable {

    @Column(nullable = false)
    private Integer serviceId;

    @Column(nullable = false)
    private Date date;

}
