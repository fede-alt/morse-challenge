package com.meli.morse.model.binary;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignalTestCase {

    @Test
    void isDurationCloserToMaxTest() {
        Signal signal = new Pulse(2);
        assertTrue(signal.isDurationCloserToMin(1,3));

        signal.setDuration(3);
        assertFalse(signal.isDurationCloserToMin(1,8));
        assertFalse(signal.isDurationCloserToMin(1,7));
        assertFalse(signal.isDurationCloserToMin(1,6));
        assertTrue(signal.isDurationCloserToMin(1,5));
        assertTrue(signal.isDurationCloserToMin(1,4));
        assertTrue(signal.isDurationCloserToMin(1,3));
    }

    @Test
    void interpretTest() {
        Signal signal = new Pulse(2);
        assertTrue(signal.isDurationCloserToMin(1,3));
    }
}