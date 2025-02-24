package com.alphabackend.utils;

import com.alphabackend.model.ErrorResponse;
import com.alphabackend.model.enum_model.ErrorTextEnum;
import com.alphabackend.model.enum_model.ErrorHttpEnum;
import org.springframework.http.HttpStatus;

import java.text.MessageFormat;

public interface Utils {

    /**
     * Concaténation d'une liste d'object type "[...]" vers un type "Object ..."
     * @param others List d'objet type "[...]"
     * @param errorHttpEnum Nom de l'exception
     * @return La liste d'objet type 'Object ..."
     */
    static Object[] concatArgs(Object[] others, ErrorHttpEnum errorHttpEnum, String labelObject) {
        Object[] result = new Object[others.length + 2];
        result[0] = errorHttpEnum.getException();
        result[1] = labelObject;
        System.arraycopy(others, 0, result, 2, others.length);
        return result;
    }

    static String concatExceptionMessage(String message, String labelObject, String typeRequest) {
        return MessageFormat.format(ErrorTextEnum.EXCEPTION_DATA.getText(), message, labelObject, typeRequest);
    }

    static ErrorResponse createErrorResponse(HttpStatus httpStatus, String message, String details, String stackTrace, String typeError) {
        return ErrorResponse.builder()
                .status(httpStatus.value())
                .message(message)
                .details(details)
                .stackTrace(stackTrace)
                .typeError(typeError)
                .build();
    }

    static ErrorResponse createErrorResponse(HttpStatus httpStatus, String message, String details, String stackTrace, String object, String typeError, String typeRequest) {
        return ErrorResponse.builder()
                .status(httpStatus.value())
                .message(message)
                .details(details)
                .stackTrace(stackTrace)
                .object(object)
                .typeError(typeError)
                .typeRequest(typeRequest)
                .build();
    }
}
