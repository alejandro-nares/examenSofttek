package com.softtek.authorization.transaction;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class ExamenMicroservicioApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExamenMicroservicioApplication.class, args);
	}

}
