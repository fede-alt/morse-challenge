package com.meli.morse.service;

import com.meli.morse.config.MorseConfiguration;
import io.swagger.annotations.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Api(tags = "morse", value = "API traduccion morse")
public class MorseServiceImpl implements MorseService {

    private final Translator translator;

    public MorseServiceImpl(MorseConfiguration config) {
        this.translator = new Translator().setDictionary(config.getDictionary());
    }

    @Override
    public ResponseEntity<MorseResponse> morse2human(MorseRequest body) throws Exception {
        try{
            return ResponseEntity.ok()
                    .body(new MorseResponse().setResponse(translator.translateMorse2Human(body.getText())));
        }catch (MorseException e){
            return ResponseEntity.badRequest()
                    .body(new MorseResponse().setError(e.getMessage()).setStatusCode(HttpStatus.BAD_REQUEST.value()));
        }
    }

    @Override
    public ResponseEntity<MorseResponse> human2morse(MorseRequest body) throws Exception {
        try{
            return ResponseEntity.ok()
                    .body(new MorseResponse().setResponse(translator.translateHuman2Morse(body.getText())));
        }catch (MorseException e){
            return ResponseEntity.badRequest()
                    .body(new MorseResponse().setError(e.getMessage()).setStatusCode(HttpStatus.BAD_REQUEST.value()));
        }
    }
}
