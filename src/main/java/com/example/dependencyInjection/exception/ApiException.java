package com.example.dependencyInjection.exception;

import org.springframework.http.HttpStatus;

public class ApiException extends RuntimeException {
    private String code;
    private HttpStatus responseStatus;

    public ApiException(String code, String message, HttpStatus status) {
        super(message);
        this.code = code;
        this.responseStatus = status;
    }

    public ApiException() {}

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public HttpStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(HttpStatus responseStatus) {
        this.responseStatus = responseStatus;
    }
}
