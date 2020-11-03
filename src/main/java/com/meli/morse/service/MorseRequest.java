package com.meli.morse.service;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class MorseRequest implements Serializable {

    @ApiModelProperty(value = "String to translate", required = true, example = "string_to_translate")
    private String text;
    @ApiModelProperty(value = "Force translation ignoring failures", position = 1, example = "false")
    private boolean coerce = false;

    public String getText() {
        return text;
    }

    public MorseRequest setText(String text) {
        this.text = text;
        return this;
    }

    public boolean getCoerce() {
        return coerce;
    }

    public MorseRequest setCoerce(boolean coerce) {
        this.coerce = coerce;
        return this;
    }
}
