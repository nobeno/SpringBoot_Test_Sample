package com.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("com.app.entity")
@SpringBootApplication(scanBasePackages = "com.app")
public class SpringBootMvcTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMvcTestApplication.class, args);
	}

}
