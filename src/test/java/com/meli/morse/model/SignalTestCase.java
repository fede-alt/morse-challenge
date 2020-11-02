package com.meli.morse.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;


class SignalTestCase {

    @Test
    void shouldPulseContinueTest() {
        Signal pulse = new Pulse();
        boolean nextBit = true; // bit 1 logico
        assertTrue(pulse.shouldContinue(nextBit));
        nextBit = false;
        assertFalse(pulse.shouldContinue(nextBit));
    }

    @Test
    void shouldPauseContinueTest() {
        Signal pause = new Pause();
        boolean nextBit = false; // bit 0 logico
        assertTrue(pause.shouldContinue(nextBit));
        nextBit = true;
        assertFalse(pause.shouldContinue(nextBit));
    }

    @Test
    void pauseInterpretation() {
//        long minPauseDuration = (long) (Math.random()*1000);
//        TransmissionContext context = new TransmissionContext().setMinPauseDuration(minPauseDuration);
//        Pause pause = new Pause();
//
//        pause.setDuration(minPauseDuration*Pause.CHAR_SPACE_TOLERANCE-1);
//        assertEquals(Pause.PAUSE, pause.interpret(context));
//
//        pause.setDuration(minPauseDuration*Pause.CHAR_SPACE_TOLERANCE);
//        assertEquals(Pause.CHAR_SPACE, pause.interpret(context));
//        pause.setDuration(minPauseDuration*Pause.WORD_SPACE_TOLERANCE-1);
//        assertEquals(Pause.CHAR_SPACE, pause.interpret(context));
//
//        pause.setDuration(minPauseDuration*Pause.WORD_SPACE_TOLERANCE);
//        assertEquals(Pause.WORD_SPACE, pause.interpret(context));
//        pause.setDuration(minPauseDuration*Pause.FULL_STOP_TOLERANCE-1);
//        assertEquals(Pause.WORD_SPACE, pause.interpret(context));
//
//        pause.setDuration(minPauseDuration*Pause.FULL_STOP_TOLERANCE);
//        assertEquals(Pause.FULL_STOP, pause.interpret(context));
    }

    @Test
    void pulseInterpretation() {
//        long minPulseDuration = (long) (Math.random()*1000);
//        TransmissionContext context = new TransmissionContext().setMinPulseDuration(minPulseDuration);
//        Pulse pulse = new Pulse();
//
//        pulse.setDuration(minPulseDuration*Pulse.DOT_TOLERANCE-1);
//        assertEquals(Pulse.DOT, pulse.interpret(context));
//        pulse.setDuration(minPulseDuration*Pulse.DOT_TOLERANCE);
//        assertEquals(Pulse.DASH, pulse.interpret(context));
    }
}