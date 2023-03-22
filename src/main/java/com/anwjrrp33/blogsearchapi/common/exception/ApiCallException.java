package com.anwjrrp33.blogsearchapi.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ApiCallException extends RuntimeException {

    public ApiCallException(String message) {
        super(message);
    }
}
