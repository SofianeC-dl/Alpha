package com.alphabackend.exception;

import com.alphabackend.model.enum_model.ErrorText;

import java.text.MessageFormat;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(ErrorText errorText) {
        super(errorText.getText());
    }

    public ResourceNotFoundException(ErrorText errorText, Object... args) {
        super(MessageFormat.format(errorText.getText(), args));
    }
}
