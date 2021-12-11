package com.example.demodb;

import org.hibernate.reactive.mutiny.Mutiny;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.persistence.Persistence;

@SpringBootApplication
public class DemoDbApplication {


	public static void main(String[] args) {
		SpringApplication.run(DemoDbApplication.class, args);
	}

	@Bean
	public Mutiny.SessionFactory sessionFactory() {
		return Persistence.createEntityManagerFactory("bookPU")
			.unwrap(Mutiny.SessionFactory.class);
	}

}
