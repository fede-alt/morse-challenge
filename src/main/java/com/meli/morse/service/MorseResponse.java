package com.meli.morse.service;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class MorseResponse implements Serializable {

    @ApiModelProperty(value = "Translated string", position = 1, example = "translated_string")
    private String response;
    @ApiModelProperty(value = "status code", position = 0, example = "200", required = true)
    private int code;
    @ApiModelProperty(value = "Error translating request text", position = 2)
    private String error;

    public String getResponse() {
        return response;
    }

    public MorseResponse setResponse(String response) {
        this.response = response;
        return this;
    }

    public int getCode() {
        return code;
    }

    public MorseResponse setCode(int code) {
        this.code = code;
        return this;
    }

    public String getError() {
        return error;
    }

    public MorseResponse setError(String error) {
        this.error = error;
        return this;
    }
}
