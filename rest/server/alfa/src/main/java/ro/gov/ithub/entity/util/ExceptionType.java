package ro.gov.ithub.entity.util;

import lombok.Getter;

/**
 * Created by Mihnea on 11/26/16.
 */
public enum ExceptionType {

    ADDED(1),
    REMOVED(2);

    @Getter
    private int type;

    ExceptionType(int exceptionType) {
        this.type = exceptionType;
    }

}
