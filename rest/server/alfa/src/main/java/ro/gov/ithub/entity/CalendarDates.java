package ro.gov.ithub.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import ro.gov.ithub.base.BaseEntity;
import ro.gov.ithub.entity.util.CalendarDatesPk;
import ro.gov.ithub.entity.util.ExceptionType;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table
public class CalendarDates implements BaseEntity {

    @EmbeddedId
    private CalendarDatesPk calendarDatesPk;

    @Enumerated(value = EnumType.ORDINAL)
    private ExceptionType exceptionType;

    @ManyToOne
    private Calendar calendar;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
