package com.example.lcsserver.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class StringValue {
    private String value;

    @JsonCreator
    public StringValue(@JsonProperty("value") String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int length() {
        return this.value.length();
    }

    public boolean contains(String substring) {
        return value.contains(substring);
    }


}
