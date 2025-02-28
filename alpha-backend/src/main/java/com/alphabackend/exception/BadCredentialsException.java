package com.alphabackend.exception;

import com.alphabackend.model.entity.ParamsError;
import com.alphabackend.utils.Utils;

/** Voir EXCEPTION_DATA("{0}|{1}|{2}"); **/
public class BadCredentialsException extends RuntimeException{
    public BadCredentialsException(ParamsError paramsError) {
        super(  Utils.concatExceptionMessage(
                        paramsError.getErrorText().getText(),
                        paramsError.getLabelObject().getName(),
                        paramsError.getTypeRequestHttp().getName()
                )
        );
    }
}
