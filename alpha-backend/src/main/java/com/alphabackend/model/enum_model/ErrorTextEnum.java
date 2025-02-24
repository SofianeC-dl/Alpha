package com.alphabackend.model.enum_model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorTextEnum{
    OBJECT_NOT_FOUND("{0}: {1} object id {2} : Not found in database"),
    ALL_OBJECTS_NOT_FOUND("{0}: No {1} objects found in database"),
    OBJECT_NONEXISTENT_DELETE("{0}: {1} object id {2} : Not found, could not be deleted"),
    OBJECT_NONEXISTENT_UPDATE("{0}: {1} object id {2} : Not found, could not be update"),

    OBJECT_UNABLE_ADD("{0}: {1} object : Unable to add object"),

    EXCEPTION_DATA("{0}|{1}|{2}");

    private final String text;
}