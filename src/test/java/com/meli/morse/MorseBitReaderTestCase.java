package com.meli.morse;

import com.meli.morse.service.MorseException;
import com.meli.morse.utils.MorseBitReader;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class MorseBitReaderTestCase {

    @Test
    public void testEveryMorseChar(){
        String holaMeli = "00000001011101000101110000000111010111";
        try {
            assertEquals(".-. .-      -.-", MorseBitReader.getInstance().decodeBits2Morse(holaMeli));
        }catch (MorseException e){
            fail(e.getMessage());
        }
    }

    @Test
    public void hola(){
        String hola = "000000000000000011011011001110000011111100111111001111110000011100111111110011100"+
                        "111000001100111111000000000";
        try {
            assertEquals(".... --- .-.. .-", MorseBitReader.getInstance().decodeBits2Morse(hola));
        }catch (MorseException e){
            fail(e.getMessage());
        }
    }

    @Test
    public void reportInterference(){
        String bits = "0101HOLA!!0101";
        try {
            MorseBitReader.getInstance().setIgnoreInterference(false).decodeBits2Morse(bits);
            fail("Deberia haber lanzado MorseException!");
        }catch (MorseException e){
            assertEquals("Bad character 'H' at position 4. (Only 1s and 0s are valid)",e.getMessage());
        }
    }

    @Test
    public void ignoreInterference(){
        String bits = "101110001interferencia100011101";
        try {
            String translation = MorseBitReader.getInstance().setIgnoreInterference(true).decodeBits2Morse(bits);
            assertEquals(".- . -.", translation);
        }catch (MorseException e){
            fail(e.getMessage());
        }
    }


}
