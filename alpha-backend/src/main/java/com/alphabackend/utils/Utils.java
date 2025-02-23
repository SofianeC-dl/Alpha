package com.alphabackend.utils;

import com.alphabackend.exception.ResourceNotFoundException;
import com.alphabackend.model.ErrorResponse;
import com.alphabackend.model.enum_model.NameException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.WebRequest;

public interface Utils {

    /**
     * Concaténation d'une liste d'object type "[...]" vers un type "Object ..."
     * @param others List d'objet type "[...]"
     * @param nameException Nom de l'exception
     * @return La liste d'objet type 'Object ..."
     */
    static Object[] concatArgs(Object[] others, NameException nameException) {
        Object[] result = new Object[others.length + 1];
        result[0] = nameException.getException();
        System.arraycopy(others, 0, result, 1, others.length);
        return result;
    }

    static ErrorResponse createErrorResponse(HttpStatus httpStatus, String message, String details, String stackTrace, String body) {
        return ErrorResponse.builder()
                .status(httpStatus.value())
                .message(message)
                .details(details)
                .stackTrace(stackTrace)
                .body(stackTrace)
                .body(body)
                .build();
    }

    static ErrorResponse createErrorResponse(HttpStatus httpStatus, String message, String details, String stackTrace) {
        return ErrorResponse.builder()
                .status(httpStatus.value())
                .message(message)
                .details(details)
                .stackTrace(stackTrace)
                .body(stackTrace)
                .build();
    }
}
