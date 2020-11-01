package com.meli.morse.service;

import com.meli.morse.model.binary.Transmission;
import com.meli.morse.utils.Translator;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@Api(tags = "morse", value = "API traduccion morse")
public class MorseServiceImpl implements MorseService {

    @Autowired
    Translator traslator;

    @Override
    public MorseResponse morse2human(MorseRequest body) throws Exception {
//        try{
        return new MorseResponse().setResponse(traslator.translateMorse2Human(body.getText()));
//        }catch (IOException e){
//
//        }
    }

    @Override
    public MorseResponse human2morse(MorseRequest body) throws Exception {
        return new MorseResponse().setResponse(traslator.translateHuman2Morse(body.getText()));
    }
}
