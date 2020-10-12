package com.iswarya.appliances.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class OperationNotAllowed extends RuntimeException {

    public OperationNotAllowed(String msg) {
        super(msg);
    }
}
