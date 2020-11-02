package com.meli.morse.config;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
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

    private HashBiMap<String,String> dictionary = HashBiMap.create();

    public HashBiMap<String, String> getDictionary() {
        return dictionary;
    }

    public MorseConfiguration setDictionary(HashBiMap<String, String> dictionary) {
        this.dictionary = dictionary;
        return this;
    }

}
