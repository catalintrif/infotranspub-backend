package ro.gov.ithub.entity.util;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Date;

@Embeddable
@Access(AccessType.FIELD)
public class CalendarDatesPk implements Serializable {

    @Column(nullable = false)
    private Integer serviceId;

    @Column(nullable = false)
    private Date date;
}
