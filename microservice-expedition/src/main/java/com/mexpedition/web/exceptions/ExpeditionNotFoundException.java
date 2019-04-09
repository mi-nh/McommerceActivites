package com.mexpedition.web.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ExpeditionNotFoundException extends RuntimeException {

    public ExpeditionNotFoundException(String message) {
        super(message);
    }
}
