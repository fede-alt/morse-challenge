package com.meli.morse;

import com.meli.morse.utils.MorseBitReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class MorseBitReaderTestCase {

    @Test
    public void simple(){
        String holaMeli = "1011101";
        try {
            assertEquals(".-.", MorseBitReader.getInstance().decodeBits2Morse(holaMeli));
        }catch (IOException e){
            fail(e.getMessage());
        }
    }

    @Test
    public void holaMeli(){
        String holaMeli = "0000000011011011001110000011111100111111001111110000011100111111110011100"+
                        "111000001100011111100001111110011111100000001100001100111111110011101110"+
                        "000001100111";
        try {
            assertEquals(".... --- .-.. .-   -- . .-- ..", MorseBitReader.getInstance().decodeBits2Morse(holaMeli));
        }catch (IOException e){
            fail(e.getMessage());
        }
    }

    @Test
    public void badString(){
        String holaMeli = "0101HOLA MELI";
        try {
            MorseBitReader.getInstance().decodeBits2Morse(holaMeli);
            fail("Deberia haber lanzado IOException!");
        }catch (IOException e){
            assertEquals("Bad character at position 4. (Only 1s and 0s are valid)",e.getMessage());
        }
    }
}
