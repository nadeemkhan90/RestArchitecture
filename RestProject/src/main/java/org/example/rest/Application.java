package org.example.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
@ComponentScan("org.example.controllers")
@ComponentScan("org.example.rest")
@ComponentScan("org.example.service")
@ComponentScan("org.example.logging")
public class Application {

	public static void main(String[] args) {		
		SpringApplication.run(Application.class, args);
	}
}
