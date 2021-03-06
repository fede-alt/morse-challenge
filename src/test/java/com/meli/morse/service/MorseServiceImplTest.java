package com.meli.morse.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MorseServiceImplTest {

    @Autowired
    private MorseServiceImpl service;

    private MorseResponse invokeHuman2MorseEndpoint(String morse) throws Exception {
        return this.service.human2morse(new MorseRequest().setText(morse)).getBody();
    }

    private MorseResponse invokeMorse2HumanEndpoint(String morse) throws Exception {
        return this.service.morse2human(new MorseRequest().setText(morse)).getBody();
    }

    private MorseResponse invokerBinary2MorseEndpoint(String binary) throws Exception {
        return this.service.binary2morse(new MorseRequest().setText(binary)).getBody();
    }

    @Test
    public void outOfTextDictionary() throws Exception {
        char badChar = '('; //char ausente en diccionario texto
        String target = "Hola "+badChar+"en morse)"; //string con ese char, (en primer lugar)
        MorseResponse response = invokeHuman2MorseEndpoint(target);
        assertEquals(400, response.getCode());
        assertEquals("Bad character '"+badChar+"' at index 5", response.getError());
    }

    @Test
    public void outOfMorseDictionary() throws Exception {
        String target = ".-------."; //termino ausente en diccionario morse
        String part1 = ".- ";
        String toTranslate = part1+target;
        MorseResponse response = invokeMorse2HumanEndpoint(toTranslate);
        assertEquals(400, response.getCode());
        assertEquals("Invalid morse term '"+target+"' at index "+part1.length()+".", response.getError());
    }

    @Test
    public void binary2morse() throws Exception {
        String binary = "010111110111111"; //..- por tolerancia 6 del application.yml de test
        String decoded = invokerBinary2MorseEndpoint(binary).getResponse();
        assertEquals("..-",decoded);
    }

    @Test
    public void superTranslationIntegrationTest() throws Exception {
        //bin2morse
        String hola = "10101010000001111110111111001111111000000101111110101000000010111111100000000000000000"; //.... --- .-.. .-
        String meli = "1111111001111110000000100000001011111101010000000101000000000000000";//-- . .-.. ..
        String decoded = invokerBinary2MorseEndpoint(hola+meli).getResponse();
        String expectedMorse = ".... --- .-.. .-   -- . .-.. ..";
        assertEquals(expectedMorse, decoded);

        //morse2text
        String translatedText = invokeMorse2HumanEndpoint(decoded).getResponse();
        assertNull(invokeMorse2HumanEndpoint(decoded).getError());
        String expectedHuman = "HOLA MELI";
        assertEquals(expectedHuman ,translatedText);


        //textBack2Morse
        MorseResponse response = invokeHuman2MorseEndpoint(translatedText);
        assertNull(response.getError());
        String morseAgain = response.getResponse();
        assertEquals(expectedMorse, morseAgain);

    }

}