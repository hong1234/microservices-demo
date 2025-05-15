package com.hong.msv;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("com.hong.msv.repository")
public class EcomProductMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(EcomProductMicroserviceApplication.class, args);
	}
}
