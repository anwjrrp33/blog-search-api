package com.anwjrrp33.blogsearchapi.common.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler({ IllegalArgumentException.class })
    public ResponseEntity<Void> handle() {
        return ResponseEntity.badRequest().build();
    }
}
