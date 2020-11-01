package com.meli.morse.utils;


import com.meli.morse.model.binary.Signal;
import com.meli.morse.model.binary.Transmission;
import sun.security.util.BitArray;

import java.io.IOException;

public class MorseBitReader {

    private static final char ONE = '1';
    private static final char ZERO = '0';
    private static MorseBitReader instance = null;

    public MorseBitReader(){
    }

    public static MorseBitReader getInstance(){
        if (instance == null){
            instance = new MorseBitReader();
        }
        return instance;
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
    public String decodeBits2Morse(BitArray bits){
        Transmission transmission = new Transmission();
        if (bits.length()>0) {
            Signal signal = SignalFactory.getInstance().create(bits.get(0));
            for (int i = 1 ; i<bits.length() ; i++){
                 boolean nextBit = bits.get(i);
                 if ( signal.continues(nextBit) ) {
                     signal.addDuration();
                 }else{
                     transmission.add(signal);
                     signal = SignalFactory.getInstance().create(nextBit);
                 }
            }
            transmission.add(signal);
            transmission.refreshContext();
        }
        return transmission.toString();
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
     * @throws  java.io.IOException
     *          Si el array no es puramente binario, es decir si existe
     *          un char "c" de modo tal que c no pertenece al conjunto {0,1}
     *
     * @return  String morse decodificado.
     *
     */
    public String decodeBits2Morse(char[] charBits) throws IOException {
        boolean[] bits = new boolean[charBits.length];
        for (int i = 0 ; i < charBits.length ; i++){
            char currentChar = charBits[i];
            if ( isValid(currentChar) ){
                bits[i] = ( currentChar == ONE );
            }else{
                throw new IOException("Bad character at position "+i+". (Only 1s and 0s are valid)");
            }
        }
        return decodeBits2Morse(new BitArray(bits));
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
    public String decodeBits2Morse(String stringBits) throws IOException {
        return decodeBits2Morse(stringBits.toCharArray());
    }


}
