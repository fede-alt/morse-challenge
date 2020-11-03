package com.meli.morse.model;

import java.util.ArrayList;
import java.util.stream.LongStream;

public class Transmission {

    private TransmissionContext context;
    private ArrayList<Signal> signalList;

    public Transmission(MorseTolerance tolerance) {
        this.signalList = new ArrayList<>();
        this.context = new TransmissionContext(tolerance);
    }

    /**
     * Refresca el contexto de una transmision en base a las sennales que la forman
     */
    public void refreshContext(){
        this.context.setMinPauseDuration( this.durationsByPause().min().orElse(1) )
                    .setMinPulseDuration( this.durationsByPulse().min().orElse(1) );
    }

    /**
     * Transforma el objeto transmision a string
     *
     * @return  String representacion string de la transmision, interpretando todas sus sennales en contexto
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Signal signal : this.signalList) {
            builder.append(signal.interpret(context));
        }
        return builder.toString();
    }

    /**
     * Agregar una sennal a la transmision
     */
    public void add(Signal signal) {
        this.signalList.add(signal);
        this.refreshContext();
    }

    /**
     * Obtiene las duraciones de los pulsos
     *
     * @return  LongStream de duraciones por pulso
     */
    private LongStream durationsByPulse(){
        return this.signalList.stream()
                .filter(signal -> (signal.getBitType() == true))
                .mapToLong(signal -> signal.getDuration());
    }

    /**
     * Obtiene las duraciones de las pausas
     *
     * @return  LongStream de duraciones por pausa
     */
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

    public void clear() {
        this.signalList.clear();
        this.refreshContext();
    }

    /**
     * Indica si la transmision tiene una pausa larga como ultima sennal
     *
     * @return  boolean
     */
    private boolean isLastSignalLongPause(){
        boolean longPause = false;
        if (!this.signalList.isEmpty()) {
            longPause = this.signalList.get(this.signalList.size()-1).interpret(context).equals(Pause.FULL_STOP);
        }
        return longPause;
    }


    /**
     * Indica si la transmision debe terminar.
     *
     * Una transmision termina luego de una pausa larga.
     *
     * @return  boolean mensaje finalizado
     */
    public boolean stop(){
        return (isLastSignalLongPause()); //|| isLastSignalFullStop());//todo soportar ambas condiciones
    }
}
