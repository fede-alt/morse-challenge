package com.meli.morse.config;

import com.google.common.collect.BiMap;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.meli.morse"})
@ConfigurationProperties(prefix = "morse")
public class MorseConfiguration {

    private BiMap<Character, String> dictionary;

    public BiMap<Character, String> getMorseDictionary() {
        return dictionary;
    }

    public MorseConfiguration setMorseDictionary(BiMap<Character, String> dictionary) {
        this.dictionary = dictionary;
        return this;
    }

    public String char2morse(char c) {
        return dictionary.get(c);
    }

    public Character morse2char(String morseString) {
        return dictionary.inverse().get(morseString);
    }
}
