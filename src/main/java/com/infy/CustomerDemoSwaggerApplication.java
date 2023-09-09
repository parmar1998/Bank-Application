package com.infy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@ComponentScan("com.infy")
@EnableWebMvc
@EnableSwagger2
public class CustomerDemoSwaggerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CustomerDemoSwaggerApplication.class, args);
	}

}
