package com.flowcontrolback.models.enums;

import lombok.Getter;

@Getter
public enum TypesActions {

    IN("in"),
    OUT("out");

    private String value;

    TypesActions(String values) {
        this.value  = values;
    }

    String getValue() {
        return value;
    }
}
