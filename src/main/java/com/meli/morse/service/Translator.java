package com.meli.morse.service;

import com.google.common.collect.BiMap;

public class Translator {

    private BiMap<String, String> dictionary;
    private static final String REGEX_MORSE_PATTERN = "(?=(?!^) )(?<! )|(?! )(?<= )";

    public String translateMorse2Human(String morse) throws MorseException {
        StringBuilder translation = new StringBuilder();
//        -------------------------------
        String[] terms = morse.split(REGEX_MORSE_PATTERN);
        long pos = 0;
        for (int i = 0 ; i < terms.length ; i++) {
            String term = terms[i];
            pos = pos + term.length();
            String translatedTerm = translate(
                    term,
                    this.dictionary.inverse(),
                    "Invalid morse term '"+term+"' at position "+pos
            );
            translation.append(translatedTerm);
        }

//        -------------------------------

//        StringBuilder pauseAcumulator = new StringBuilder();
//        StringBuilder pulseAcumulator = new StringBuilder();
//        for (int i = 0 ; i < morse.length() ; i++){
//            char morseChar = morse.charAt(i);
//            if (morseChar == ' ') {
//                pauseAcumulator.append(morseChar);
//                translation.append(translate(pulseAcumulator.toString(),this.dictionary.inverse(),"Invalid morse term '"+pulseAcumulator.toString()+"' at position "+(i-pulseAcumulator.toString().length())));
//                pulseAcumulator = new StringBuilder();
//            }else{
//                pulseAcumulator.append(morseChar);
//                translation.append(translate(pauseAcumulator.toString(),this.dictionary.inverse(),"Invalid morse term '"+pauseAcumulator.toString()+"' at position "+(i-pulseAcumulator.toString().length())));
//                pauseAcumulator = new StringBuilder();
//            }
//        }
        return translation.toString().trim();
    }

    private String translate(String target, BiMap<String, String> dictionary, String error) throws MorseException {
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
                            this.dictionary,
                            "Bad character '"+text.charAt(i)+"' at position "+i)+" ");
        }
        return translation.toString().trim();
    }


    public BiMap<String, String> getDictionary() {
        return dictionary;
    }

    public Translator setDictionary(BiMap<String, String> dictionary) {
        this.dictionary = dictionary;
        //spaces
        dictionary.put(" ","  ");
        dictionary.put(""," ");
        return this;
    }

}
