package com.meli.morse.service;

public class MorseException extends Exception{

    private String message;
    public MorseException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public MorseException setMessage(String message) {
        this.message = message;
        return this;
    }
}
