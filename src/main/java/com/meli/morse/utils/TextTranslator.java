package com.meli.morse.utils;

import com.meli.morse.service.MorseException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@ConfigurationProperties(prefix = "translator")
public class TextTranslator implements Translator {

    private HashMap<String, String> dictionary = new HashMap<>();
    private static final String SPACE = " ";

    /**
     * Traduce un String de texto "humano" a morse.
     * <p> Recorre un string y traduce caracter por caracter
     *
     * @param  text
     *         String texto humano a traducir, idealmente formado por caracteres pertenecientes al diccionario
     *
     * @param  coercion
     *         boolean: forzar traduccion, ignorando fallos (puede llevar a resultados no deseados)
     *
     * @throws  MorseException
     *          Es lanzada si no se fuerza la traduccion (coerce=false) y ademas falla la traduccion
     *
     * @return  String texto morse.
     *
     */
    public String translate(String text, boolean coercion) throws MorseException {
        StringBuilder translation = new StringBuilder();
        text = filterSpaces(text);
        for (int i = 0 ; i < text.length() ; i++){
            translation.append(
                    fetchFromDictionary(
                            String.valueOf(text.charAt(i)).toUpperCase(),
                            coercion,
                            i)
                    ).append(SPACE);//(*)
        }
        return translation.toString().trim();
    }

    @Override
    public String translate(String morse) throws MorseException {
        return translate(morse, false);
    }

    @Override
    public String fetchFromDictionary(String target, boolean coerce, int parsingIndex) throws MorseException {
        String translation = dictionary.get(target);
        if (translation != null) {
            return translation;
        }else{
            if (coerce)
                return "";
            else
                throw new MorseException("Bad character '"+target+"' at index "+parsingIndex);
        }
    }

    /**
     * Manejo de espacios textString equivalentes
     * <p> Sea un termino con espacios intermedios multiples (que tomo sin significado), los llevo a uno solo.
     */
    public String filterSpaces(String stringText) {
        StringBuilder result = new StringBuilder();
        for (String word : stringText.split("\\s+")){
            result.append(word).append(" ");
        }
        return result.toString().trim();
    }

    @Override
    public HashMap<String, String> getDictionary() {
        return this.dictionary;
    }

    @Override
    public void setDictionary(HashMap<String, String> dictionary) {
        this.dictionary = dictionary;
        this.dictionary.put(SPACE, SPACE); //identidad de espacios teniendo en cuenta el append (*)
    }


}
