package com.flowcontrolback.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

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
