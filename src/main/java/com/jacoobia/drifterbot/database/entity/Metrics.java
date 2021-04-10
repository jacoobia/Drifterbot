package com.jacoobia.drifterbot.database.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Metrics implements Serializable
{

    private static final long serialVersionUID = -6836116108587116119L;

    private Integer id;
    private String name;
    private Integer value;

}