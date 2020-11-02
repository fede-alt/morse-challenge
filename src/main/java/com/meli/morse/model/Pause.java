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
    public static final String WORD_SPACE = "      ";
    public static final String FULL_STOP = "\n";
    public static final int CHAR_SPACE_TOLERANCE = 3;
    public static final int WORD_SPACE_TOLERANCE = 7;
    public static final int FULL_STOP_TOLERANCE = 10;

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
        if (this.getDuration() >= minPauseDuration*FULL_STOP_TOLERANCE){
            return FULL_STOP;
        }else if (this.getDuration() >= minPauseDuration*WORD_SPACE_TOLERANCE){
            return WORD_SPACE;
        }else if (this.getDuration() >= minPauseDuration*CHAR_SPACE_TOLERANCE){
            return CHAR_SPACE;
        }else{
            return PAUSE;
        }
    }

    public boolean getBitType() {
        return false;
    }

}
