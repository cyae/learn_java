package com.learn.springboot.backend_manager;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@MapperScan("com.learn.springboot.backend_manager.mapper")
@ServletComponentScan(basePackages = "com.learn.springboot.backend_manager")
@SpringBootApplication
public class BackendManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendManagerApplication.class, args);
	}
	
}
