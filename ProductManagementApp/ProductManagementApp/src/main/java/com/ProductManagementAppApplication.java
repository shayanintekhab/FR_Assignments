package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



@SpringBootApplication(scanBasePackages = "com") // Enable @repository, @service and @controller 
@EntityScan(basePackages = "com.bean")   // enable @Entity ORM annotation 
@EnableJpaRepositories(basePackages = "com.dao")
public class ProductManagementAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductManagementAppApplication.class, args);
		System.out.println("Spring boot is up");
	}

}
