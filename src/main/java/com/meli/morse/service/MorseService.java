package com.meli.morse.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public interface MorseService {

    @RequestMapping(value = "/2text", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    ResponseEntity<MorseResponse> morse2human(@RequestBody MorseRequest body) throws Exception;

    @RequestMapping(value = "/2morse", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    ResponseEntity<MorseResponse>  human2morse(@RequestBody MorseRequest body) throws Exception;

}
