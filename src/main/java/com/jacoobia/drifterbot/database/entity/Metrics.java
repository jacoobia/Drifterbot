package com.jacoobia.drifterbot.database.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Represents our drifter_metrics table, or, more accurately
 * each instance of this object will represent a single row in
 * the drifter_metrics table.
 */
@Getter
@Setter
public class Metrics implements Serializable
{

    private static final long serialVersionUID = -6836116108587116119L;

    private Integer id;
    private String name;
    private Integer value;

}