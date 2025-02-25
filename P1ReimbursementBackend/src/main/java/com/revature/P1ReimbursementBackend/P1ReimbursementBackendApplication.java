package com.revature.P1ReimbursementBackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan("com.revature.models")
@ComponentScan("com.revature")
@EnableJpaRepositories("com.revature.DAOs")
public class P1ReimbursementBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(P1ReimbursementBackendApplication.class, args);
		System.out.println("Reimbursement app running");
	}

}
