package com.sunx.mysprmdbapp1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*", maxAge = 3600)
@SpringBootApplication
@EnableMongoRepositories
public class MyspringMdbApp1Application {

	private static final Logger LOGGER = LogManager.getLogger(MyspringMdbApp1Application.class);

	public static void main(String[] args) {
		LOGGER.info("Starting JWT based Restful-API Demo App...");
		SpringApplication.run(MyspringMdbApp1Application.class, args);
	}
	
}
	
