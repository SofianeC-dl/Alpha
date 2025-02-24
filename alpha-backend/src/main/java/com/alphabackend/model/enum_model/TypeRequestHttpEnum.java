package com.alphabackend.model.enum_model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TypeRequestHttpEnum {
    GET_REQUEST("GET"),
    POST_REQUEST("POST"),
    PUT_REQUEST("PUT"),
    DELETE_REQUEST("DELETE");

    private final String name;
}
