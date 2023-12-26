package com.example.dependencyInjection.controllers;

import jakarta.annotation.Nullable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicController {
    @RequestMapping("/ping")
    public String pingMe(@RequestParam @Nullable String pingBack) {
        return pingBack != null ? pingBack : "OK";
    }
}
