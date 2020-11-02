package com.meli.morse.service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "Morse Translator", value = "Morse translator API")
public interface MorseService {


    @RequestMapping(value = "/2text", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ApiOperation("Translates well formed morse string to text")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Succesfull translation"),
            @ApiResponse(code = 400, message = "Failed to translate"),
            @ApiResponse(code = 500, message = "Internal server error") })
    ResponseEntity<MorseResponse> morse2human(@RequestBody MorseRequest body) throws Exception;


    @RequestMapping(value = "/2morse", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ApiOperation("Translates text to morse")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Succesfull translation"),
            @ApiResponse(code = 400, message = "Failed to translate"),
            @ApiResponse(code = 500, message = "Internal server error") })
    ResponseEntity<MorseResponse>  human2morse(@RequestBody MorseRequest body) throws Exception;


    @RequestMapping(value = "/binary2morse", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    @ApiOperation("Translates binary string to morse")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Succesfull translation"),
            @ApiResponse(code = 400, message = "Failed to translate"),
            @ApiResponse(code = 500, message = "Internal server error") })
    ResponseEntity<MorseResponse>  binary2morse(@RequestBody MorseRequest body) throws Exception;

}
