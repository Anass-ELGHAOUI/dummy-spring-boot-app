package com.dummy.quickdirtyblog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public final class OperationNotAllowedException extends RuntimeException{

    public OperationNotAllowedException(String message) {
        super(message);
    }
}
