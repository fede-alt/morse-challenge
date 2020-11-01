package com.meli.morse.service;

import java.io.Serializable;

public class MorseRequest implements Serializable {

    private String text;

    public String getText() {
        return text;
    }

    public MorseRequest setText(String text) {
        this.text = text;
        return this;
    }
}
