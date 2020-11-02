package com.meli.morse.service;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class MorseRequest implements Serializable {

    @NotNull
    private String text;

    public String getText() {
        return text;
    }

    public MorseRequest setText(String text) {
        this.text = text;
        return this;
    }
}
