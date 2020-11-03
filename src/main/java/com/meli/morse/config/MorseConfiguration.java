package com.meli.morse.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
@EnableConfigurationProperties
@ComponentScan(basePackages = {"com.meli.morse"})
@ConfigurationProperties(prefix = "morse")
public class MorseConfiguration {

    private HashMap<String,String> dictionary = new HashMap<>();

    public HashMap<String, String> getDictionary() {
        return dictionary;
    }

    public MorseConfiguration setDictionary(HashMap<String, String> dictionary) {
        this.dictionary = dictionary;
        return this;
    }

}
