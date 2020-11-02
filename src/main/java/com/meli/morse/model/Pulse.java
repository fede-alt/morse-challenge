package com.meli.morse.model;

/**
 * Un {@code Pulse} representa un segmento de bits en alto
 * pertenecientes a la transmision (un pulso).
 * <p>Este segmento solo esta formado por UNOs
 * @see  Pause
 * @see  Signal
 * @see  Transmission
 *
 */
public class Pulse extends Signal {

    public static final String DOT = ".";
    public static final String DASH = "-";
    public static final int DOT_TOLERANCE = 3;

    public Pulse(int duration) {
        super(duration);
    }

    public Pulse() {
        super();
    }


    /**
     * Decodifica el pulso a un String morse, segun el contexto.
     *
     * @return  String ( . or - )
     *
     */
    @Override
    public String interpret(TransmissionContext context) {
        if (this.getDuration() >= context.getMinPulseDuration()*DOT_TOLERANCE){
            return DASH;
        }else{
            return DOT;
        }
    }

    public boolean getBitType() {
        return true;
    }

}