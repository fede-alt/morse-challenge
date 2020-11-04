package com.meli.morse.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.meli.morse.model.Pause.WORD_SPACE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TextTranslatorTestCase {

    @Autowired
    TextTranslator textTranslator;

    @Test
    public void filterSpaces() {
        assertEquals("hola a aa a a", textTranslator.filterSpaces("hola     a aa a a"));
    }

    @Test
    public void morse2humanDictionary() {
        //Testeo el diccionario completo (morse->texto)
        try {
            for (String morse : textTranslator.getDictionary().keySet()){
                String translation = textTranslator.translate(morse);
                if (!translation.trim().isEmpty())
                    assertEquals(translation, textTranslator.getDictionary().get(morse));
            }
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }
}