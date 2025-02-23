package com.alphabackend.exception;

import com.alphabackend.model.enum_model.ErrorText;
import com.alphabackend.model.enum_model.NameException;
import com.alphabackend.model.enum_model.NameObject;
import com.alphabackend.utils.Utils;

import java.text.MessageFormat;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(ErrorText errorText, Object... args) {
        super(MessageFormat.format(errorText.getText(), Utils.concatArgs(args, NameException.RESOURCE_NOT_FOUND)));
    }
}
