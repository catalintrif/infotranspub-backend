package ro.gov.ithub.entity.util;

import lombok.Getter;

/**
 * Created by Mihnea on 11/26/16.
 */
public enum FareAttributePaymentMethod {

    PAID_ON_BOARD(0),
    PAID_BEFORE_BOARDING(1);

    @Getter
    private int paymentMethod;

    FareAttributePaymentMethod(int method) {
        this.paymentMethod = method;
    }
}
