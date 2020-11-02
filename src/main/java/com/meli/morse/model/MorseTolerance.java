package com.meli.morse.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "tolerance")
public class MorseTolerance {

    private long dash = 3;
    private long charSpace = 3;
    private long wordSpace = 7;
    private long fullStop = 10;

    public long getDash() {
        return dash;
    }

    public MorseTolerance setDash(long dash) {
        if (dash > 1){
            this.dash = dash;
        }
        return this;
    }

    public long getCharSpace() {
        return charSpace;
    }

    public MorseTolerance setCharSpace(long charSpace) {
        if (charSpace > 1){
            this.charSpace = charSpace;
        }
        return this;
    }

    public long getWordSpace() {
        return wordSpace;
    }

    public MorseTolerance setWordSpace(long wordSpace) {
        if (wordSpace > this.charSpace){
            this.wordSpace = wordSpace;
        }else{
            this.wordSpace = this.charSpace*3;
        }
        return this;
    }

    public long getFullStop() {
        return fullStop;
    }

    public MorseTolerance setFullStop(long fullStop) {
        if (fullStop > this.wordSpace){
            this.fullStop = fullStop;
        }else{
            this.fullStop = this.wordSpace*3;
        }
        return this;
    }
}
