package com.meli.morse.utils;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class MorseBitReaderTest {

    @Test
    void stringDecodeBits2MorseTestCase() {
        String decoded;
        try{
            decoded = MorseBitReader.getInstance().decodeBits2Morse("HOLA");
        }catch (IOException e){
            assertEquals("Bad character at position 0. (Only 1s and 0s are valid)", e.getMessage());
        }

        try{
            decoded = MorseBitReader.getInstance().decodeBits2Morse("1011");
            assertEquals(".-", decoded);
        }catch (IOException e){
            fail();
        }

    }

    @Test
    void bitArrayDecodeBits2MorseTestCase() {
    }

    @Test
    void charArrayDecodeBits2MorseTestCase() {
    }
}