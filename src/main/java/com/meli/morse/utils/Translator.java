package com.meli.morse.utils;

import com.google.common.collect.BiMap;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("singleton")
public class Translator {

    private BiMap<Character, String> dictionary;

    public String translateMorse2Human(String morse){
        return "HOLA";
    }

    public String translateHuman2Morse(String human){
        return ".-.-.----.- -. -. -. - -. .- ";
    }
}
