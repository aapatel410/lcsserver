package com.example.lcsserver.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SetOfStrings{
    private List<StringValue> setOfStrings;

    public SetOfStrings(){}

    @JsonCreator
    public SetOfStrings(@JsonProperty("setOfStrings") List<StringValue> setOfStrings) {
        this.setOfStrings = setOfStrings;
    }
    public List<StringValue> getSetOfStrings() {
        return setOfStrings;
    }

    public void setSetOfStrings(List<StringValue> setOfStrings) {
        this.setOfStrings = setOfStrings;
    }
}



