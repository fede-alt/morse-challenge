package com.meli.morse.utils;

import com.meli.morse.model.MorseTolerance;
import com.meli.morse.model.Signal;
import com.meli.morse.model.Transmission;
import com.meli.morse.service.MorseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class MorseBitReader {

    private static final char ONE = '1';
    private static final char ZERO = '0';

    @Autowired
    private MorseTolerance tolerance;

    /**
     * Decodifica un BitArray a codigo morse.
     *
     * @param  bits
     *         BitArray a decodificar.
     *
     * @return  String morse decodificado.
     *
     */
    public String decodeBits2Morse(ArrayList<Boolean> bits){
        Transmission transmission = new Transmission(tolerance);
        if (bits.size()>0) {
            Signal signal = SignalFactory.getInstance().createFrom(bits.get(0));
            for (int i = 1 ; i < bits.size() ; i++){
                boolean nextBit = bits.get(i);
                if ( signal.shouldContinue(nextBit) ) {
                    signal.addDuration();
                }else{
                    //handle end of signal
                    transmission.add(signal);
                    signal = SignalFactory.getInstance().createFrom(nextBit);
                }
            }
            transmission.add(signal); //add last processed signal
            transmission.refreshContext();
        }
        return transmission.toString().trim();
    }


    /**
     * Valida un caracter
     *
     * @return  valor booleano del caracter perteneciente al coonjunto {0,1}
     */
    private boolean isValid(char c){
        return ( c == ONE | c == ZERO );
    }


    /**
     * Decodifica un array de caracteres a codigo morse.
     *
     * @param  charBits
     *         char[] a decodificar.
     *
     * @throws  com.meli.morse.service.MorseException
     *          Al no ignorar interferencia, si el array no es puramente binario, es
     *          decir si existe un char "c" de modo tal que c no pertenece al conjunto {0,1}
     *
     * @return  String morse decodificado.
     *
     */
    public String decodeBits2Morse(char[] charBits, boolean coerce) throws MorseException {
        ArrayList<Boolean> bits = new ArrayList<>();
        for (int i = 0 ; i < charBits.length ; i++){
            char currentChar = charBits[i];
            if ( isValid(currentChar) ){
                bits.add( currentChar == ONE );
            }else{
                if (!coerce)
                    throw new MorseException("Bad character '"+currentChar+"' at position "+i+"." +
                            " (Only 1s and 0s are valid)");
            }
        }
        return decodeBits2Morse(bits);
    }


    /**
     * Decodifica el string a codigo morse.
     *
     * @param  stringBits
     *         String a decodificar.
     *
     * @throws  java.io.IOException
     *          Si el string no es puramente binario, es decir si tiene
     *          un char C de modo tal que C no pertenece al conjunto {0,1}
     *
     * @return  String morse decodificado.
     *
     */
    public String decodeBits2Morse(String stringBits, boolean coerce) throws MorseException {
        return decodeBits2Morse(stringBits.toCharArray(), coerce);
    }


}
