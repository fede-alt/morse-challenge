package com.meli.morse.model;

import java.util.ArrayList;
import java.util.stream.LongStream;

public class Transmission {

    private TransmissionContext context;
    private ArrayList<Signal> signalList;

    public Transmission() {
        this.signalList = new ArrayList<>();
        this.context = new TransmissionContext();
    }

    public void refreshContext(){
        this.context.setMinPauseDuration( this.durationsByPause().min().orElse(1) )
                    .setMinPulseDuration( this.durationsByPulse().min().orElse(1) );
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Signal signal : this.signalList) {
            builder.append(signal.interpret(context));
        }
        return builder.toString();
    }

    public void add(Signal signal) {
        this.signalList.add(signal);
        this.refreshContext();
    }

    private LongStream durationsByPulse(){
        return this.signalList.stream()
                .filter(signal -> (signal.getBitType() == true))
                .mapToLong(signal -> signal.getDuration());
    }

    private LongStream durationsByPause(){
        return this.signalList.stream()
                .filter(signal -> (signal.getBitType() == false))
                .mapToLong(signal -> signal.getDuration());
    }

    public TransmissionContext getContext() {
        return context;
    }

    public Transmission setContext(TransmissionContext context) {
        this.context = context;
        return this;
    }

    public ArrayList<Signal> getSignals() {
        return signalList;
    }

    public Transmission setSignals(ArrayList<Signal> signals) {
        this.signalList = signals;
        return this;
    }
}
