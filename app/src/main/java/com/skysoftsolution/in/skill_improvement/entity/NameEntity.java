package com.skysoftsolution.in.skill_improvement.entity;

import java.io.Serializable;

public class NameEntity implements Serializable {
    private String Name;
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
}