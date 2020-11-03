package com.meli.morse.utils;

import com.google.common.collect.BiMap;
import com.meli.morse.service.MorseException;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Translator {

    private HashMap<String, String> dictionary;
    private HashMap<String, String> textLUT;
    private Map<String, String> morseLUT;
    private static final String REGEX_SPACING_PATTERN = "(?=(?!^) )(?<! )|(?! )(?<= )";

    public String translateMorse2Human(String morse) throws MorseException {
        StringBuilder translation = new StringBuilder();
        String[] terms = morse.split(REGEX_SPACING_PATTERN);
        long pos = 0;
        for (int i = 0 ; i < terms.length ; i++) {
            String term = terms[i];
            String translatedTerm = translate(
                    term,
                    morseLUT,
                    "Invalid morse term '"+term+"' at position "+pos
            );
            pos = pos + term.length();
            translation.append(translatedTerm);
        }
        return translation.toString().trim();
    }

    private String translate(String target, Map<String, String> dictionary, String error) throws MorseException {
        String translation = dictionary.get(target);
        if (translation != null) {
            return translation;
        }else{
            throw new MorseException(error);
        }
    }

    public String translateHuman2Morse(String text) throws MorseException {
        StringBuilder translation = new StringBuilder();
        for (int i = 0 ; i < text.length() ; i++){
            translation.append(
                    translate(
                            String.valueOf(text.charAt(i)).toUpperCase(),
                            textLUT,
                            "Bad character '"+text.charAt(i)+"' at position "+i)+" ");
        }
        return translation.toString().trim();
    }

    public Map<String,String> getMorseLUT(){
        return this.dictionary.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getValue,Map.Entry::getKey));
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
        //spaces
        dictionary.put(" "," ");
        this.textLUT = dictionary;
        this.morseLUT = this.dictionary.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getValue,Map.Entry::getKey));
        this.morseLUT.put(" ","");
        this.morseLUT.put("   "," ");
        return this;
    }

}
