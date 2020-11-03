package com.meli.morse.model;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SignalTestCase {

    @Autowired MorseTolerance tolerance;

    @Test
    public void shouldPulseContinueTest() {
        Signal pulse = new Pulse();
        boolean nextBit = true; // bit 1 logico
        assertTrue(pulse.shouldContinue(nextBit));
        nextBit = false;
        assertFalse(pulse.shouldContinue(nextBit));
    }

    @Test
    public void shouldPauseContinueTest() {
        Signal pause = new Pause();
        boolean nextBit = false; // bit 0 logico
        assertTrue(pause.shouldContinue(nextBit));
        nextBit = true;
        assertFalse(pause.shouldContinue(nextBit));
    }

    @Test
    public void pauseInterpretation() {
        long minPauseDuration = (long) (Math.random()*1000);
        TransmissionContext context = new TransmissionContext(tolerance)
                .setMinPauseDuration(minPauseDuration);
        Pause pause = new Pause();

        pause.setDuration(minPauseDuration*tolerance.getCharSpace()-1);
        assertEquals(Pause.PAUSE, pause.interpret(context));

        pause.setDuration(minPauseDuration*tolerance.getCharSpace());
        assertEquals(Pause.CHAR_SPACE, pause.interpret(context));
        pause.setDuration(minPauseDuration*tolerance.getWordSpace()-1);
        assertEquals(Pause.CHAR_SPACE, pause.interpret(context));

        pause.setDuration(minPauseDuration*tolerance.getWordSpace());
        assertEquals(Pause.WORD_SPACE, pause.interpret(context));
        pause.setDuration(minPauseDuration*tolerance.getFullStop()-1);
        assertEquals(Pause.WORD_SPACE, pause.interpret(context));

        pause.setDuration(minPauseDuration*tolerance.getFullStop());
        assertEquals(Pause.FULL_STOP, pause.interpret(context));
    }

    @Test
    public void pulseInterpretation() {
        long minPulseDuration = (long) (Math.random()*1000);
        TransmissionContext context = new TransmissionContext(tolerance)
                .setMinPulseDuration(minPulseDuration);
        Pulse pulse = new Pulse();

        pulse.setDuration(minPulseDuration*tolerance.getDash()-1);
        assertEquals(Pulse.DOT, pulse.interpret(context));
        pulse.setDuration(minPulseDuration*tolerance.getDash());
        assertEquals(Pulse.DASH, pulse.interpret(context));
    }
}