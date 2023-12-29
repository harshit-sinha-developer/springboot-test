package com.example.dependencyInjection.exception;

import org.springframework.http.HttpStatus;

public class AuthenticationException extends ApiException {
    public AuthenticationException(String message) {
        super("authentication_error", message, HttpStatus.UNAUTHORIZED);
    }

    public AuthenticationException(String code, String message) {
        super(code, message, HttpStatus.UNAUTHORIZED);
    }
}
