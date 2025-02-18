package com.alphabackend.model.enum_model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorText{
    OBJECT_NOT_FOUND("{0} object id {1} : Not found in database"),
    ALL_OBJECTS_NOT_FOUND("No {0} objects found in database"),
    OBJECT_NOT_DELETE("{0} object id {1} : Could not be deleted");

    private final String text;
}