package com.udemy.springsecurity.exceptionhandling;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({AuthenticationException.class})
    public ResponseEntity<?> handleAuthenticationException(Exception exception) {
        return ResponseEntity
                .status(401)
                .contentType(MediaType.APPLICATION_JSON)
                .body(String.format("{\"msg\": \"%s\"}", exception.getMessage()));
    }

    @ExceptionHandler({AccessDeniedException.class})
    public ResponseEntity<?> handleAccessDeniedException(Exception exception) {
        return ResponseEntity
                .status(403)
                .contentType(MediaType.APPLICATION_JSON)
                .body(String.format("{\"msg\": \"%s\"}", exception.getMessage()));
    }
}
