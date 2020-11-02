package com.meli.morse.service;

import com.meli.morse.config.MorseConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MorseServiceImpl implements MorseService {

    private final Translator translator;

    public MorseServiceImpl(MorseConfiguration config) {
        this.translator = new Translator().setDictionary(config.getDictionary());
    }

    @Override
    public ResponseEntity<MorseResponse> morse2human(MorseRequest body) throws Exception {
        try{
            return ResponseEntity.ok()
                    .body(new MorseResponse()
                            .setResponse(translator.translateMorse2Human(body.getText()))
                            .setCode(HttpStatus.OK.value()));
        }catch (MorseException e){
            return ResponseEntity.badRequest()
                    .body(new MorseResponse().setError(e.getMessage()).setCode(HttpStatus.BAD_REQUEST.value()));
        }
    }

    @Override
    public ResponseEntity<MorseResponse> human2morse(MorseRequest body) throws Exception {
        try{
            return ResponseEntity.ok()
                    .body(new MorseResponse()
                            .setResponse(translator.translateHuman2Morse(body.getText()))
                            .setCode(HttpStatus.OK.value()));
        }catch (MorseException e){
            return ResponseEntity.badRequest()
                    .body(new MorseResponse().setError(e.getMessage()).setCode(HttpStatus.BAD_REQUEST.value()));
        }
    }

    @Override
    public ResponseEntity<MorseResponse> binary2morse(MorseRequest body) throws Exception {
        return null;
    }
}
