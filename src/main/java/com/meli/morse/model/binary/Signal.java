package com.meli.morse.model.binary;

/**
 * Una {@code Signal} representa un segmento de bits
 * pertenecientes a la transmision.
 *
 * <p>Este segmento puede estar formado por bits en alto (Pulse)
 * o por bits en bajo (Pause)
 *
 * @see  Pulse
 * @see  Pause
 * @see  Transmission
 *
 */
public abstract class Signal {

    private long duration;

    public Signal(int duration){
        this.duration = duration;
        if ( this.duration < 0 ){
            this.duration = 1;
        }
    }

    public Signal(){
        this.duration = 1;
    }

    public long getDuration() {
        return this.duration;
    }

    public void addDuration() {
        this.duration++;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean continues(boolean nextBit){
        return (this.getBit() == nextBit);
    }


    /**
     * Decodifica la sennal a un String morse, segun el contexto.
     *
     * @return  String morse.
     *
     * @see  Pulse
     * @see  Pause
     */
    public abstract String decode(TransmissionContext context);


    /**
     * Devuelve el valor booleano de los bits que forman la sennal.
     *
     * @return  true si esta formado por UNOs,
     *          false en caso contrario.
     *
     */
    public abstract boolean getBit();


}

