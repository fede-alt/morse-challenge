package com.meli.morse.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SignalFactoryTestCase {

    @Test
    void createFrom() {
        assertTrue(SignalFactory.getInstance().createFrom(true).getBitType());
    }
}