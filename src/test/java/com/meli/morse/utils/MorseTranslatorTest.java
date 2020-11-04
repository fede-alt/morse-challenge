package com.meli.morse.utils;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.meli.morse.model.Pause.CHAR_SPACE;
import static com.meli.morse.model.Pause.WORD_SPACE;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MorseTranslatorTest {

    @Autowired
    MorseTranslator morseTranslator;


    @Test
    public void filterSpaces() {
        assertEquals(WORD_SPACE, morseTranslator.filterSpaces("          "));
        assertEquals(CHAR_SPACE, morseTranslator.filterSpaces(" "));
    }

    @Test
    public void morse2humanDictionary() {
        //Testeo el diccionario completo (morse->texto)
        try {
            for (String morse : morseTranslator.getDictionary().keySet()){
                String translation = morseTranslator.translate(morse);
                if (!translation.trim().isEmpty())
                    assertEquals(translation, morseTranslator.getDictionary().get(morse));
            }
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }


}