package com.example.dependencyInjection;

import org.springframework.stereotype.Component;

@Component
public class MyRequestClient {
    public String makeRequest(String url) {
        System.out.println("Making request to URL:" + url);
        String result = "Some dummy data";
        System.out.println("Got result :" + result);
        return result;
    }
}
