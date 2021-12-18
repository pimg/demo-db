package com.example.demodb.repositories.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static javax.persistence.Persistence.createEntityManagerFactory;
import static org.hibernate.reactive.mutiny.Mutiny.SessionFactory;

@Configuration
public class HibernateConfig {

	@Bean
	public SessionFactory sessionFactory() {
		return createEntityManagerFactory("bookPU")
			.unwrap(SessionFactory.class);
	}


	//TODO this is not yet supported with hibernate-reactive, resulting that @Transaction cannot be used
//	@Bean
//	public PlatformTransactionManager hibernateTransactionManager(org.hibernate.SessionFactory sessionFactory) {
//		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
//
//		transactionManager.setSessionFactory(sessionFactory);
//		return transactionManager;
//	}

}
