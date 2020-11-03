package com.meli.morse.model;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransmissionTestCase {

    @Autowired
    private MorseTolerance tolerance;

    @Test
    public void interpretSimpleContext() {
        Transmission transmission = new Transmission(tolerance);

        transmission.add(new Pulse(1));
        transmission.add(new Pause(2));
        transmission.add(new Pulse(1));

        assertEquals("..",transmission.toString());
    }

    @Test
    public void interpretEmptyTransmission() {
        Transmission transmission = new Transmission(tolerance);
        assertEquals("", transmission.toString());
    }

    @Test
    public void interpretSingleSignalTransmission() {
        Transmission transmission = new Transmission(tolerance);
        transmission.add(new Pulse(2));
        assertEquals(".", transmission.toString());

        transmission = new Transmission(tolerance);
        transmission.add(new Pause(2));
        assertEquals("", transmission.toString());
    }

}