package com.meli.morse.model;

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

    /**
     * Obtiene la duracion de la sennal
     *
     * @return  long signal's duration value
     *
     */
    public long getDuration() {
        return this.duration;
    }

    public void addDuration() {
        this.duration++;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    /**
     * Dado un bit siguiente este metodo determina si la sennal debe continuar.
     * <p>La sennal continua si el proximo bit es del mismo tipo de los que forman la sennal
     *
     * @param nextBit (proximo bit)
     *
     * @return  booleano de continuacion
     *
     * @see  Pulse
     * @see  Pause
     */
    public boolean shouldContinue(boolean nextBit){
        return (this.getBitType() == nextBit);
    }


    /**
     * Decodifica la sennal a un String morse, segun el contexto.
     *
     * @return  String morse.
     *
     * @see  Pulse
     * @see  Pause
     */
    public abstract String interpret(TransmissionContext context);


    /**
     * Devuelve el valor booleano de los bits que forman la sennal.
     *
     * @return  true si esta formado por UNOs,
     *          false en caso contrario.
     *
     */
    public abstract boolean getBitType();


}

