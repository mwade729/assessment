package org.mwade.assessment.services;

import lombok.Data;

@Data
public class ParseException extends Exception {

    public ParseException(String message, int line) {
        super(String.format("Line %d: %s", line, message));
    }
}
