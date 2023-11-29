package com.example.dependencyInjection;

import org.springframework.stereotype.Component;

@Component
public class DBModule {
    public void save(String result) {
        System.out.println("Saving to DB.....");
        // Performs actual save to DB
        System.out.println("Results saved");
    }
}
