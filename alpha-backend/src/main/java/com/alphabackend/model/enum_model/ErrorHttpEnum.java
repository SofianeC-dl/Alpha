package com.alphabackend.model.enum_model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorHttpEnum{
    RESOURCE_NOT_FOUND("ResourceNotFoundException"),
    BAD_CREDENTIALS("BadCredentialsException"),
    INTERNAL_ERROR("InternalErrorException"),;

    private final String exception;
}
