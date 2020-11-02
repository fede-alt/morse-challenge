package com.meli.morse.utils;

import com.meli.morse.service.MorseException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

class MorseBitReaderTest {

    @Test
    void stringDecodeBits2MorseTestCase() {
        try{
            MorseBitReader.getInstance().decodeBits2Morse("010UNO10");
            fail("debe lanzar MorseException");
        }catch (MorseException e){
            assertEquals("Bad character at position 3. (Only 1s and 0s are valid)", e.getMessage());
        }

        try{
            String decoded = MorseBitReader.getInstance().decodeBits2Morse("10111");
            assertEquals(".-", decoded);
        }catch (Exception e){
            fail("Esta prueba no deberia lanzar una exception.");
        }
    }

}