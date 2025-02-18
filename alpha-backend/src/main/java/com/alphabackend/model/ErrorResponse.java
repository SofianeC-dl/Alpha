package com.alphabackend.model;

import lombok.*;

@Data
@Builder
public class ErrorResponse {
    private int status;
    private String message;
    private String details;

    public ErrorResponse(int status, String message, String details){
        this.status = status;
        this.message = message;
        this.details = details;
    }
}
