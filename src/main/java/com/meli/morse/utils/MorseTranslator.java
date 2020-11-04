package com.meli.morse.utils;

import com.meli.morse.service.MorseException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static com.meli.morse.model.Pause.*;

@Component
@ConfigurationProperties(prefix = "translator")
public class MorseTranslator implements Translator {

    private HashMap<String, String> dictionary = new HashMap<>();
    private static final String REGEX_SPACING_PATTERN = "(?=(?!^) )(?<! )|(?! )(?<= )";

    /**
     * Traduccion default sin forzado.
     */
    @Override
    public String translate(String morse) throws MorseException {
        return this.translate(morse, false);
    }

    /**
     * Decodifica el string a codigo morse.
     * <p> Recorre un string morse traduciendo termino a termino, separados por espacios...
     *
     * @param  morse
     *         String morse a traducir.
     *
     * @param  coerce
     *         boolean: forzar traduccion, ignorando fallos (puede llevar a resultados no deseados).
     *
     * @throws  MorseException
     *          Es lanzada si no se fuerza la traduccion (coerce=false) y ademas falla la traduccion
     *
     * @return  String texto "humano".
     *
     */
    @Override
    public String translate(String morse, boolean coerce) throws MorseException {
        StringBuilder translation = new StringBuilder();
        String[] terms = morse.split(REGEX_SPACING_PATTERN);
        int parsingIndex = 0;
        for (String term : terms) {
            term = filterSpaces(term);
            String translatedTerm = fetchFromDictionary(term, coerce, parsingIndex);
            parsingIndex += term.length();
            translation.append(translatedTerm);
        }
        return translation.toString().trim();
    }

    @Override
    public String fetchFromDictionary(String term, boolean coerce, int parsingIndex) throws MorseException {
        String translation = dictionary.get(term);
        if (translation != null) {
            return translation;
        }else{
            if (coerce)
                return ""; //ignorar traduccion
            else
                throw new MorseException("Invalid morse term '" + term + "' at index " + parsingIndex+".");
        }
    }


    /**
     * Manejo de espacios morseStrings
     * <p> Sea un termino morse formado por espacios se retorna una estandarizacion para traducir espacios.
     *  Si hay mas de 1 espacio, entonces es un espacio entre palabras, caso contrario es un espacio entre chars.
     *  No tiene sentido que hayan mas de 3 espacios (espacio entre palabras).
     */
    public String filterSpaces(String morseTerm) {
        if (morseTerm.trim().isEmpty()){
            return (morseTerm.length()>1) ? WORD_SPACE : CHAR_SPACE;
        }else {
            return morseTerm;
        }
    }

    @Override
    public HashMap<String, String> getDictionary() {
        return this.dictionary;
    }

    @Override
    public void setDictionary(HashMap<String, String> dictionary) {
        this.dictionary = (HashMap<String, String>) dictionary.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getValue,Map.Entry::getKey));
        this.dictionary.put(CHAR_SPACE,""); //ignorar char spaces
        this.dictionary.put(WORD_SPACE," "); //word space
        this.dictionary.put(PAUSE,""); //equivalencia trivial para ignorar pausas
    }


}
