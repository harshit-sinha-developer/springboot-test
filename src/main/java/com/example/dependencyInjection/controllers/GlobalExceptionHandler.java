package com.example.dependencyInjection.controllers;

import com.example.dependencyInjection.exception.NotFoundException;
import com.example.dependencyInjection.model.ApiError;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {NotFoundException.class})
    public ResponseEntity<ApiError> handleNotFound(NotFoundException exception) {
        return new ResponseEntity<ApiError>(new ApiError(exception.getMessage(), exception.getCode()), HttpStatus.NOT_FOUND);
    }
}
