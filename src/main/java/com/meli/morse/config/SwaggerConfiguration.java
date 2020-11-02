package com.meli.morse.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    private ApiInfo DEFAULT_API_INFO = new ApiInfo(
        "API Morse Translator",
        "This service provides morse translation.",
        "1.0.0-00",
        "",
        new Contact("Federico Bogovic", "", "fedebogovic@gmail.com"),
        "",
        "",
        new ArrayList<>()
    );


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
            .useDefaultResponseMessages(false)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.meli.morse.service"))
            .build()
            .apiInfo(DEFAULT_API_INFO);
    }

}