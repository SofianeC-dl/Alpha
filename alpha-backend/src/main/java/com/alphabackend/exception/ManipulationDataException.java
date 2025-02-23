package com.alphabackend.exception;

import com.alphabackend.model.enum_model.ErrorText;
import com.alphabackend.model.enum_model.NameException;
import com.alphabackend.utils.Utils;

import java.text.MessageFormat;

public class ManipulationDataException extends RuntimeException{
    public ManipulationDataException(ErrorText errorText, Object... args) {
        super(MessageFormat.format(errorText.getText(), Utils.concatArgs(args, NameException.MANIPULATION_DATA)));
    }
}
