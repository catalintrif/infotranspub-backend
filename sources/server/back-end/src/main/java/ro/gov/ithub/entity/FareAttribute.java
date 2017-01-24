package ro.gov.ithub.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.builder.ToStringBuilder;
import ro.gov.ithub.base.BaseEntity;
import ro.gov.ithub.entity.util.FareAttributePaymentMethod;
import ro.gov.ithub.entity.util.FareAttributeTransfers;

import javax.persistence.*;
import java.util.Currency;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@Entity
@Table
public class FareAttribute implements BaseEntity {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Access(AccessType.PROPERTY)
    private Integer fareId;

    @ManyToOne
    private FareRule fareRule;

    @Column(nullable = false)
    private Double price;

    @Embedded
    private String currency;

    @Enumerated
    private FareAttributePaymentMethod paymentMethod;

    @Enumerated
    private FareAttributeTransfers transfers;

    @Column
    private Integer transferDuration;

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public Currency getCurrency() {
        return Currency.getInstance(currency);
    }

    public void setCurrency(Currency fareCurrency) {
        this.currency = fareCurrency.getCurrencyCode();
    }
}
