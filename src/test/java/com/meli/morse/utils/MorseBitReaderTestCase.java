package com.meli.morse.utils;

import com.meli.morse.service.MorseException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MorseBitReaderTestCase {

    @Autowired
    private MorseBitReader bitReader;

    @Test
    public void reportInterferenceTest() {
        char interferencia = 'Z';
        String target = "01"+interferencia+"01";
        try{
            bitReader.decodeBits2Morse("01"+interferencia+"0",false);
            fail("debe lanzar MorseException");
        }catch (MorseException e){
            assertEquals("Bad character '"+interferencia+"' at position "+target.indexOf(interferencia)+"." +
                    " (Only 1s and 0s are valid)", e.getMessage());
        }
    }

    @Test
    public void ignoreInterferenceTest() throws MorseException {
        char interferencia = 'Z';
        assertEquals(".-.",bitReader.decodeBits2Morse("101111"+interferencia+"1101",true));
    }

    @Test
    public void toleranceIntegrationTest() throws MorseException {
        String decoded = bitReader.decodeBits2Morse("1011111",false);//guion = 5 puntos
        assertEquals("..", decoded);
        decoded = bitReader.decodeBits2Morse("101111111",false);//guion = 6 puntos
        assertEquals(".-", decoded);
    }

    @Test
    public void holaMeli() throws MorseException {
        String hola = "10101010000001111110111111001111111000000101111110101000000010111111100000000000000000"; //.... --- .-.. .-
        String meli = "1111111001111110000000100000001011111101010000000101000000000000000";//-- . .-.. ..
        String decoded = bitReader.decodeBits2Morse(hola+meli,false);
        assertEquals(".... --- .-.. .-   -- . .-.. ..", decoded);
    }

    @Test
    public void transmissionIntegratedStopTest() throws MorseException {
        String preStop = "01010111111";
        String transmission = preStop+"0000000000000000000000000000011111111101010101010";
        String decoded = bitReader.decodeBits2Morse(transmission, false);
        String preStopDecoded = bitReader.decodeBits2Morse(preStop, false);
        assertEquals(preStopDecoded, decoded);
    }

}