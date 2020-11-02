package com.meli.morse.service;

import com.google.common.collect.BiMap;

public class Translator {

    private BiMap<String, String> dictionary;

    public String translateMorse2Human(String morse) throws MorseException {
        return translateFromDictionary(morse, this.dictionary.inverse());
    }

    public String translateHuman2Morse(String human) throws MorseException {
        return translateFromDictionary(human.toUpperCase(), this.dictionary);
    }

    private String translateFromDictionary(String toTranslate, BiMap<String,String> dictionary) throws MorseException {
        StringBuilder builder = new StringBuilder();
        for (int i = 0 ; i < toTranslate.length() ; i++){
            String translation = dictionary.get(String.valueOf(toTranslate.charAt(i)));
            if (translation != null) {
                builder.append(translation+" ");
            }else{
                throw new MorseException("Could not translate character at index "+i+".");
            }
        }
        return builder.toString().trim();
    }

    public BiMap<String, String> getDictionary() {
        return dictionary;
    }

    public Translator setDictionary(BiMap<String, String> dictionary) {
        this.dictionary = dictionary;
        //spaces
        dictionary.put(" ","   ");
        dictionary.put(""," ");
        dictionary.put("SPACE","");

        return this;
    }

}
