package com.example.dependencyInjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FetcherSaver {
    @Autowired
    private MyRequestClient requestClient;

    @Autowired
    private DBModule dbModule;


    private String fetch() {
        return requestClient.makeRequest("http://google.com");
    }

    private void save(String resultToSave) {
        dbModule.save(resultToSave);
    }

    public void fetchAndSave() {
        String result = fetch();
        save(result);
    }
}
