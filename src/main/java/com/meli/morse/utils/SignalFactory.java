package com.meli.morse.utils;

import com.meli.morse.model.binary.Pause;
import com.meli.morse.model.binary.Pulse;
import com.meli.morse.model.binary.Signal;

public class SignalFactory {

    private final static char ONE = '1';
    private static SignalFactory instance = null;

    public SignalFactory(){
    }

    public static SignalFactory getInstance(){
        if (instance == null){
            instance = new SignalFactory();
        }
        return instance;
    }

    public Signal create(String signal) {
        return (signal.charAt(0) == ONE) ? new Pulse(signal.length()) : new Pause(signal.length());
    }

    public Signal create(boolean isPulse) {
        return (isPulse) ? new Pulse() : new Pause();
    }
}
