package com.alphabackend.model.enum_model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum NameException {
    RESOURCE_NOT_FOUND("ResourceNorFoundException"),
    MANIPULATION_DATA("ManipulationDataException");

    private final String exception;
}
