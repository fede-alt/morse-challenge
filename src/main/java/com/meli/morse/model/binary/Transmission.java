package com.meli.morse.model.binary;

import java.util.ArrayList;
import java.util.stream.LongStream;

public class Transmission extends ArrayList<Signal> {

    private TransmissionContext context;

    public Transmission() {
        super();
        this.context = new TransmissionContext();
    }

    public void refreshContext(){
        this.context.setMinPauseDuration( this.durationsByPause().min().orElse(1) )
                    .setMinPulseDuration( this.durationsByPulse().max().orElse(1) );
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Signal signal : this) {
            builder.append(signal.decode(context));
        }
        return builder.toString();
    }


    @Override
    public boolean add(Signal signal) {
        boolean added;
        added = super.add(signal);
        this.refreshContext();
        return added;
    }

    private LongStream durationsByPulse(){
        return this.stream().filter(signal -> (signal.getBit())).mapToLong(signal -> signal.getDuration());
    }

    private LongStream durationsByPause(){
        return this.stream().filter(signal -> (!signal.getBit())).mapToLong(signal -> signal.getDuration());
    }

    public TransmissionContext getContext() {
        return context;
    }

    public Transmission setContext(TransmissionContext context) {
        this.context = context;
        return this;
    }

}
