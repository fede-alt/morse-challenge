package com.meli.morse.model.binary;

/**
 * Un {@code Pulse} representa un segmento de bits en alto
 * pertenecientes a la transmision (un pulso).
 * <p>Este segmento solo esta formado por UNOs
 * @see  com.meli.morse.model.binary.Pause
 * @see  com.meli.morse.model.binary.Signal
 * @see  com.meli.morse.model.binary.Transmission
 *
 */
public class Pulse extends Signal {

    public static final String DOT = ".";
    public static final String DASH = "-";

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
    public String decode(TransmissionContext context) {
        return context.interpret(this);
    }

    public boolean getBit() {
        return true;
    }

}