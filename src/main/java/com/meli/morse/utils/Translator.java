package com.meli.morse.utils;

import com.meli.morse.service.MorseException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@ConfigurationProperties(prefix = "translator")
public class Translator {

    private HashMap<String, String> dictionary = new HashMap<>();
    private HashMap<String, String> textLUT;
    private HashMap<String, String> morseLUT;
    private static final String REGEX_SPACING_PATTERN = "(?=(?!^) )(?<! )|(?! )(?<= )";

    /**
     * Decodifica el string a codigo morse.
     * <p> Recorre un string morse traduciendo termino a termino, separados por espacios
     *
     * @param  morse
     *         String morse a traducir.
     *
     * @param  coercion
     *         boolean: forzar traduccion, ignorando fallos (puede llevar a resultados no deseados)
     *
     * @throws  com.meli.morse.service.MorseException
     *          Es lanzada si no se fuerza la traduccion (coerce=false) y ademas falla la traduccion
     *
     * @return  String texto "humano".
     *
     */
    public String translateMorse2Human(String morse, boolean coercion) throws MorseException {
        StringBuilder translation = new StringBuilder();
        String[] terms = morse.split(REGEX_SPACING_PATTERN);
        long pos = 0;
        for (int i = 0 ; i < terms.length ; i++) {
            String term = terms[i];
            String translatedTerm = translate(
                    term,
                    morseLUT,
                    coercion,
                    "Invalid morse term '"+term+"' at position "+pos
            );
            pos = pos + term.length();
            translation.append(translatedTerm);
        }
        return translation.toString().trim();
    }


    /**
     * Traduce un String a otro utilizando los diccionarios pre-establecidos.
     *
     * @param  target
     *         String a traducir (caracter textual o termino morse).
     *
     * @param  coerce
     *         boolean: si este valor es true y falla la traduccion se devuelve string vacio a modo de omitir esta traduccion
     *
     * @throws  com.meli.morse.service.MorseException
     *          Es lanzada si no se fuerza la traduccion (coerce=false) y no existe la clave en el dccionario
     *
     * @return  String target traducido.
     *
     */
    private String translate(String target, Map<String, String> dictionary, boolean coerce ,String error) throws MorseException {
        String translation = dictionary.get(target);
        if (translation != null) {
            return translation;
        }else{
            if (coerce)
                return "";
            else
                throw new MorseException(error);
        }
    }

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
     * @throws  com.meli.morse.service.MorseException
     *          Es lanzada si no se fuerza la traduccion (coerce=false) y ademas falla la traduccion
     *
     * @return  String texto morse.
     *
     */
    public String translateHuman2Morse(String text, boolean coercion) throws MorseException {
        StringBuilder translation = new StringBuilder();
        for (int i = 0 ; i < text.length() ; i++){
            translation.append(
                    translate(
                            String.valueOf(text.charAt(i)).toUpperCase(),
                            textLUT,
                            coercion,
                            "Bad character '"+text.charAt(i)+"' at position "+i)+" ");
        }
        return translation.toString().trim();
    }

    //------------LOOK UP TABLES----------------

    public HashMap<String,String> getMorseLUT(){
        return this.morseLUT;
    }

    public Map<String, String> getTextLUT(){
        return this.dictionary;
    }

    public Translator setTextLUT(HashMap<String, String> textLUT) {
        this.textLUT = textLUT;
        return this;
    }

    public Translator setMorseLUT(HashMap<String, String> morseLUT) {
        this.morseLUT = morseLUT;
        return this;
    }

    public Translator setDictionary(HashMap<String, String> dictionary) {
        this.dictionary = dictionary;
        //spaces handling
        dictionary.put(" "," ");
        this.textLUT = dictionary;
        this.morseLUT = (HashMap<String, String>) this.dictionary.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getValue,Map.Entry::getKey));
        this.morseLUT.put(" ","");
        this.morseLUT.put("   "," ");
        return this;
    }

    //------------LOOK UP TABLES----------------

}
