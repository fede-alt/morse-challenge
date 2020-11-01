package com.meli.morse.model.binary;

import static com.meli.morse.model.binary.Pulse.*;
import static com.meli.morse.model.binary.Pause.*;

/**
 * {@code TransmissionContext} define el contexto de la transmision.
 *
 * <p>Es capaz de interpretar Signal
 *
 * @see  Signal
 * @see  Transmission
 *
 */
public class TransmissionContext {

    private static final int DOT_TOLERANCE = 3;
    private static final int CHAR_SPACE_TOLERANCE = 3;
    private static final int WORD_SPACE_TOLERANCE = 7;
    private static final int FULL_STOP_TOLERANCE = 10;

    private long minPulseDuration;
    private long minPauseDuration;

    public TransmissionContext(){
    }

    public String interpret(Pulse signal){
        if (signal.getDuration() >= minPulseDuration*DOT_TOLERANCE){
            return DASH;
        }else{
            return DOT;
        }
    }

    public String interpret(Pause signal){
        if (signal.getDuration() >= minPauseDuration*FULL_STOP_TOLERANCE){
            return FULL_STOP;
        }else if (signal.getDuration() >= minPauseDuration*WORD_SPACE_TOLERANCE){
            return WORD_SPACE;
        }else if (signal.getDuration() >= minPauseDuration*CHAR_SPACE_TOLERANCE){
            return CHAR_SPACE;
        }else{
            return PAUSE;
        }
    }

    public long getMinPulseDuration() {
        return minPulseDuration;
    }

    public TransmissionContext setMinPulseDuration(long minPulseDuration) {
        this.minPulseDuration = minPulseDuration;
        return this;
    }

    public long getMinPauseDuration() {
        return minPauseDuration;
    }

    public TransmissionContext setMinPauseDuration(long minPauseDuration) {
        this.minPauseDuration = minPauseDuration;
        return this;
    }
}
