package com.meli.morse.service;

import com.meli.morse.utils.MorseBitReader;
import com.meli.morse.utils.MorseTranslator;
import com.meli.morse.utils.TextTranslator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MorseServiceImpl implements MorseService {

    private final MorseTranslator morseTranslator;
    private final TextTranslator textTranslator;
    private final MorseBitReader binaryDecoder;

    public MorseServiceImpl(MorseTranslator morseTranslator, TextTranslator textTranslator, MorseBitReader bitReader) {
        this.binaryDecoder = bitReader;
        this.textTranslator = textTranslator;
        this.morseTranslator = morseTranslator;
    }

    @Override
    public ResponseEntity<MorseResponse> morse2human(MorseRequest body) throws Exception {
        try{
            boolean coercion = body.getCoerce();
            String translation = morseTranslator.translate(body.getText(), coercion);
            return translationOk(translation);
        }catch (MorseException e){
            return handleMorseException(e);
        }
    }

    @Override
    public ResponseEntity<MorseResponse> human2morse(MorseRequest body) throws Exception {
        try{
            String translation = textTranslator.translate(body.getText(), body.getCoerce());
            return translationOk(translation);
        }catch (MorseException e){
            return handleMorseException(e);
        }
    }

    @Override
    public ResponseEntity<MorseResponse> binary2morse(MorseRequest body) throws Exception {
        try{
            String translation = binaryDecoder.decodeBits2Morse(body.getText(),body.getCoerce());
            return translationOk(translation);
        }catch (MorseException e){
            return handleMorseException(e);
        }
    }

    private ResponseEntity<MorseResponse> handleMorseException(MorseException e){
        return ResponseEntity.badRequest()
                .body(new MorseResponse().setError(e.getMessage()).setCode(HttpStatus.BAD_REQUEST.value()));
    }

    private ResponseEntity<MorseResponse> translationOk(String translation){
        return ResponseEntity.ok()
                .body(new MorseResponse()
                        .setResponse(translation)
                        .setCode(HttpStatus.OK.value()));
    }
}
