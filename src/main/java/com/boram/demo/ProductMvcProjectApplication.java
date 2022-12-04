package com.boram.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.boram")
@EntityScan(basePackages = "com.boram.entity")
@EnableJpaRepositories(basePackages = "com.boram.model.persistence")
public class ProductMvcProjectApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductMvcProjectApplication.class, args);
	}

}
