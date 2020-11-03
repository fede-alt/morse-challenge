package com.meli.morse.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "tolerance")
public class MorseTolerance {

    public static final long DEFAULT_DASH_TOLERANCE = 3;
    public static final long DEFAULT_CHAR_SPACE_TOLERANCE = 3;
    public static final long DEFAULT_WORD_SPACE_TOLERANCE = 7;
    public static final long DEFAULT_FULL_STOP_TOLERANCE = 10;

    private long dash = DEFAULT_DASH_TOLERANCE;
    private long charSpace = DEFAULT_CHAR_SPACE_TOLERANCE;
    private long wordSpace = DEFAULT_WORD_SPACE_TOLERANCE;
    private long fullStop = DEFAULT_FULL_STOP_TOLERANCE;

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
