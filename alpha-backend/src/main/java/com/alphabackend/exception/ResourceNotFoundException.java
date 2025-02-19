package com.alphabackend.exception;

import com.alphabackend.model.enum_model.ErrorText;
import com.alphabackend.model.enum_model.NameException;

import java.text.MessageFormat;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(ErrorText errorText, Object... args) {
        super(MessageFormat.format(errorText.getText(), concatArgs(args)));
    }

    private static Object[] concatArgs(Object[] others) {
        Object[] result = new Object[others.length + 1];
        result[0] = NameException.RESOURCE_NOT_FOUND.getException();
        System.arraycopy(others, 0, result, 1, others.length);
        return result;
    }
}
