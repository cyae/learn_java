package com.learn.microservice.service_2;

import java.io.IOException;
import java.net.UnknownHostException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Service2Application {

	public static void main(String[] args) throws UnknownHostException, IOException {
		SpringApplication.run(Service2Application.class, args);
		Client.start();
	}

}
