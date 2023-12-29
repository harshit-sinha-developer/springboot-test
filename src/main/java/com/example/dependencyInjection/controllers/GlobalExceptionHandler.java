package com.example.dependencyInjection.controllers;

import com.example.dependencyInjection.exception.ApiException;
import com.example.dependencyInjection.model.ApiError;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {ApiException.class})
    public ResponseEntity<ApiError> handleApiException(ApiException exception) {
        return new ResponseEntity<>(
            new ApiError(exception.getMessage(), exception.getCode()),
            exception.getResponseStatus()
        );
    }
}
