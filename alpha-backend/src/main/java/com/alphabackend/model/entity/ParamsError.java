package com.alphabackend.model.entity;

import com.alphabackend.model.enum_model.ErrorTextEnum;
import com.alphabackend.model.enum_model.NameObject;
import com.alphabackend.model.enum_model.TypeRequestHttpEnum;
import lombok.Builder;
import lombok.Data;
import lombok.Singular;

import java.util.List;

@Data
@Builder
public class ParamsError {
    ErrorTextEnum       errorText;
    NameObject          labelObject;
    TypeRequestHttpEnum typeRequestHttp;

    @Singular
    List<Object> args;
}
