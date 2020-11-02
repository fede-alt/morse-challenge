package com.meli.morse.utils;

import com.meli.morse.model.Pause;
import com.meli.morse.model.Pulse;
import com.meli.morse.model.Signal;

public class SignalFactory {

    private static SignalFactory instance = null;

    public SignalFactory(){
    }

    public static SignalFactory getInstance(){
        if (instance == null){
            instance = new SignalFactory();
        }
        return instance;
    }

    public Signal createFrom(boolean isPulse) {
        return (isPulse) ? new Pulse() : new Pause();
    }
}
