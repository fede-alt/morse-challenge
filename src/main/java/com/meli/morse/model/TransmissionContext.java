package com.meli.morse.model;

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
}
