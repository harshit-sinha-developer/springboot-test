package com.example.dependencyInjection.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PublicController {
    @RequestMapping("/ping")
    public String pingMe(@RequestParam String ping) {
        return ping;
    }
}
