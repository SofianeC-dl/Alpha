package com.alphabackend.exception;

import com.alphabackend.model.ParamsException;
import com.alphabackend.model.enum_model.ErrorHttpEnum;
import com.alphabackend.utils.Utils;

import java.text.MessageFormat;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(ParamsException paramsException) {
        super(  Utils.concatExceptionMessage(
                MessageFormat.format(
                        paramsException.getErrorText().getText(),
                    Utils.concatArgs(
                            paramsException.getArgs().toArray(new Object[0]), ErrorHttpEnum.RESOURCE_NOT_FOUND,
                            paramsException.getLabelObject().getName()
                    )
                ),
                paramsException.getLabelObject().getName(),
                paramsException.getTypeRequestHttp().getName()
                ) // Voir EXCEPTION_DATA("{0}|{1}|{2}");
        );
    }
}
