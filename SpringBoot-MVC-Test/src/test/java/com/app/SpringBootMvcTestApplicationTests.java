package com.app;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

//@RunWith(SpringRunner.class)
@EntityScan("com.app.entity")
@SpringBootApplication(scanBasePackages = "com.app.dao")
public class SpringBootMvcTestApplicationTests {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootMvcTestApplicationTests.class, args);
	}
}
