package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HelloEc2JenkinsApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelloEc2JenkinsApplication.class, args);
		System.out.println("Spring boot is up");
	}

}
