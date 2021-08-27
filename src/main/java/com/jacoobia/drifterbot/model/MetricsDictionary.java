package com.jacoobia.drifterbot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * A dictionary of the metrics ID's just for easily
 * grabbing them from the database without the use of
 * magic numbers.
 */
@Getter
@AllArgsConstructor
public enum MetricsDictionary {

    UNIQUE_USERS(1),
    TOTAL_DINGS(2),
    MOTES_BANKED(3);

    private final int id;

}
