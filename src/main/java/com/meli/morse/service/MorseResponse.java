package com.meli.morse.service;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

public class MorseResponse implements Serializable {

    private String response;
    private int statusCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String error;

    public String getResponse() {
        return response;
    }

    public MorseResponse setResponse(String response) {
        this.response = response;
        return this;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public MorseResponse setStatusCode(int statusCode) {
        this.statusCode = statusCode;
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
