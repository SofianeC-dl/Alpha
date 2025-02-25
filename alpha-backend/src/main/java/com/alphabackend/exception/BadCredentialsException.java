package com.alphabackend.exception;

import com.alphabackend.model.entity.ParamsException;
import com.alphabackend.utils.Utils;

public class BadCredentialsException extends RuntimeException{
    public BadCredentialsException(ParamsException paramsException) {
        super(  Utils.concatExceptionMessage(
                        paramsException.getErrorText().getText(),
                        paramsException.getLabelObject().getName(),
                        paramsException.getTypeRequestHttp().getName()
                ) // Voir EXCEPTION_DATA("{0}|{1}|{2}");
        );
    }
}
