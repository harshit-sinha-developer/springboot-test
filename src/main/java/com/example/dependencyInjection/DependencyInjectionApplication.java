package com.example.dependencyInjection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DependencyInjectionApplication {


	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(DependencyInjectionApplication.class, args);

//		DBModule dbModule = context.getBean(DBModule.class);
//		MyRequestClient client = context.getBean(MyRequestClient.class);
//		FetcherSaver fetcherSaver = context.getBean(FetcherSaver.class);
//		fetcherSaver.fetchAndSave();
	}

}
