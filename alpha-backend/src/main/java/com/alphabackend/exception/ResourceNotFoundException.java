package com.alphabackend.exception;

import com.alphabackend.model.entity.ParamsError;
import com.alphabackend.model.enum_model.ErrorHttpEnum;
import com.alphabackend.utils.Utils;

import java.text.MessageFormat;

public class ResourceNotFoundException extends RuntimeException {
    /**
     * Message exception construit sous le format EXCEPTION_DATA("{0}|{1}|{2}");
     * @param paramsError
     */
    public ResourceNotFoundException(ParamsError paramsError) {
        super(  Utils.concatExceptionMessage(
                MessageFormat.format(
                        paramsError.getErrorText().getText(),
                    Utils.concatArgs(
                            paramsError.getArgs().toArray(new Object[0]), ErrorHttpEnum.RESOURCE_NOT_FOUND,
                            paramsError.getLabelObject().getName()
                    )
                ),
                paramsError.getLabelObject().getName(),
                paramsError.getTypeRequestHttp().getName()
                )
        );
    }
}
