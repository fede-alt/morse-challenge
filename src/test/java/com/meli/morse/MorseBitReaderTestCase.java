package com.meli.morse;

import com.meli.morse.utils.MorseBitReader;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class MorseBitReaderTestCase {



    @Test
    public void holaMeli(){
        String holaMeli= "0000000011011011001110000011111100011111100111111000000011101111111101110"+
                        "1110000000110001111110000011111100111111000000011000011011111111011101110"+
                        "00000110111";
        try {

            assertEquals(".... --- .-.. .-   -- . .-- ..", MorseBitReader.getInstance().decodeBits2Morse(holaMeli));
        }catch (IOException e){
            fail(e.getMessage());
        }
    }
}
