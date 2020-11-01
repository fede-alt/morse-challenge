package com.meli.morse.model.binary;

/**
 * Una {@code Pause} representa un segmento de bits en bajo
 * pertenecientes a la transmision (una pausa).
 * <p>Este segmento solo esta formado por CEROs
 * @see  com.meli.morse.model.binary.Pulse
 * @see  com.meli.morse.model.binary.Signal
 * @see  com.meli.morse.model.binary.Transmission
 *
 */
public class Pause extends Signal {

    public static final String PAUSE = "";
    public static final String CHAR_SPACE = " ";
    public static final String WORD_SPACE = "      ";
    public static final String FULL_STOP = "\n";

    public Pause(int duration) {
        super(duration);
    }

    public Pause() {
        super();
    }

    /**
     * Decodifica la pausa a un String morse, segun el contexto.
     *
     * @return  String ( . or - )
     *
     */
    @Override
    public String decode(TransmissionContext context) {
        return context.interpret(this);
    }

    public boolean getBit() {
        return false;
    }

}
