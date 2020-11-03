package com.meli.morse.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SignalFactoryTestCase {

    @Test
    public void createFrom() {
        assertTrue(SignalFactory.getInstance().createFrom(true).getBitType());
    }
}