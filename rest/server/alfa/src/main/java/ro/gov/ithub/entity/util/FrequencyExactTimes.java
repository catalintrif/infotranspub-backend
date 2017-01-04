package ro.gov.ithub.entity.util;

import lombok.Getter;

/**
 * Created by Mihnea on 11/26/16.
 */
public enum FrequencyExactTimes {

    UNSCHEDULED(0),
    EXACTLY_SCHEDULED(1);

    @Getter
    private int exactTimes;

    FrequencyExactTimes(int exactTimes) { this.exactTimes = exactTimes; }
}
