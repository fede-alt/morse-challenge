package com.meli.morse.model;

/**
 * Una {@code Pause} representa un segmento de bits en bajo
 * pertenecientes a la transmision (una pausa).
 *
 * <p>Este segmento solo esta formado por CEROs
 *
 * @see  Pulse
 * @see  Signal
 * @see  Transmission
 *
 */
public class Pause extends Signal {

    public static final String PAUSE = "";
    public static final String CHAR_SPACE = " ";
    public static final String WORD_SPACE = "   ";
    public static final String FULL_STOP = ".";

    public Pause(int duration) {
        super(duration);
    }

    public Pause() {
        super();
    }

    /**
     * Decodifica la pausa a un String morse, segun el contexto.
     *
     * @return  String PAUSE = ""
     */
    public String interpret(TransmissionContext context) {
        long minPauseDuration = context.getMinPauseDuration();
        if ( this.getDuration() >= minPauseDuration * context.getTolerance().getFullStop() ){
            return FULL_STOP;
        }else if ( this.getDuration() >= minPauseDuration * context.getTolerance().getWordSpace() ){
            return WORD_SPACE;
        }else if ( this.getDuration() >= minPauseDuration * context.getTolerance().getCharSpace() ){
            return CHAR_SPACE;
        }else{
            return PAUSE;
        }
    }

    public boolean getBitType() {
        return false;
    }

}
