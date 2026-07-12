package com.emmanuel.accountservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class NexusBankApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(NexusBankApiApplication.class, args);
	}

}
