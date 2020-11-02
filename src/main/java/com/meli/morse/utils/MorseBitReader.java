package com.meli.morse.utils;

import com.meli.morse.model.Signal;
import com.meli.morse.model.Transmission;
import com.meli.morse.service.MorseException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
@ConfigurationProperties(prefix = "bit-reader")
public class MorseBitReader {

    private static final char ONE = '1';
    private static final char ZERO = '0';
    private static MorseBitReader instance = null;
    private boolean ignoreInterference;

    public static MorseBitReader getInstance(){
        if (instance == null){
            instance = new MorseBitReader();
        }
        return instance;
    }

    public MorseBitReader setIgnoreInterference(boolean ignoreInterference) {
        this.ignoreInterference = ignoreInterference;
        return this;
    }

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
        Transmission transmission = new Transmission();
        if (bits.size()>0) {
            Signal signal = SignalFactory.getInstance().createFrom(bits.get(0));
            for (boolean nextBit : bits){
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
    public String decodeBits2Morse(char[] charBits) throws MorseException {
        ArrayList<Boolean> bits = new ArrayList<>();
        for (int i = 0 ; i < charBits.length ; i++){
            char currentChar = charBits[i];
            if ( isValid(currentChar) ){
                bits.add( currentChar == ONE );
            }else{
                //handle interference
                if (!ignoreInterference)
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
    public String decodeBits2Morse(String stringBits) throws MorseException {
        return decodeBits2Morse(stringBits.toCharArray());
    }


}
