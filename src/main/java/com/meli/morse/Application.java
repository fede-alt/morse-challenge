package com.meli.morse;

import com.meli.morse.config.MorseConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(new Class[]{MorseConfiguration.class}, args);
    }
}
