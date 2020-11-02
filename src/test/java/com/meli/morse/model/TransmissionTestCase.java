package com.meli.morse.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TransmissionTestCase {

    @Test
    void interpretSimpleContext() {
        Transmission transmission = new Transmission();

        transmission.add(new Pulse(1));
        transmission.add(new Pause(2));
        transmission.add(new Pulse(1));

        assertEquals("..",transmission.toString());
    }

    @Test
    void interpretEmptyTransmission() {
        Transmission transmission = new Transmission();
        assertEquals("", transmission.toString());
    }

    @Test
    void interpretSingleSignalTransmission() {
        Transmission transmission = new Transmission();
        transmission.add(new Pulse(2));
        assertEquals(".", transmission.toString());

        transmission = new Transmission();
        transmission.add(new Pause(2));
        assertEquals("", transmission.toString());
    }


}