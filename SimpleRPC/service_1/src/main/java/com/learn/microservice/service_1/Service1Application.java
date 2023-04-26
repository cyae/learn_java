package com.learn.microservice.service_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Service1Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Service1Application.class, args);
		Server.start();
	}

}
