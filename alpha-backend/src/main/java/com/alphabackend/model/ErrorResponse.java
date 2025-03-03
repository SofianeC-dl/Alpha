package com.alphabackend.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private int status;
    private String message;
    private String details;
    private String stackTrace;
    private String object;
    private String typeError;
    private String typeRequest;
}
