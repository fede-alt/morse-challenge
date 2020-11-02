package com.meli.morse.model;

import org.springframework.beans.factory.annotation.Autowired;

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

    private long minPulseDuration;
    private long minPauseDuration;

    @Autowired
    private MorseTolerance tolerance;

    public TransmissionContext(){
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

    public MorseTolerance getTolerance() {
        return tolerance;
    }

    public TransmissionContext setTolerance(MorseTolerance tolerance) {
        this.tolerance = tolerance;
        return this;
    }
}
