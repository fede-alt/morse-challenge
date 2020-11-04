package com.meli.morse.utils;

import com.meli.morse.service.MorseException;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;


public interface Translator {

    String translate(String morse, boolean coercion) throws MorseException;

    String translate(String morse) throws MorseException;

    String fetchFromDictionary(String target, boolean coerce, int parsingIndex)throws MorseException;

    String filterSpaces(String toFilter);

    public HashMap<String,String> getDictionary();

    void setDictionary(HashMap<String,String> dictionary);
}
