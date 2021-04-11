package com.jacoobia.drifterbot.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MetricsDictionary
{

    UNIQUE_USERS(1),
    TOTAL_DINGS(2),
    MOTES_BANKED(3);

    private final int id;

}
