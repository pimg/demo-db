package com.example.demodb.repositories.impl;

import com.example.demodb.domains.Book;
import com.example.demodb.repositories.BookRepository;
import io.smallrye.mutiny.Uni;
import lombok.AllArgsConstructor;

import static org.hibernate.reactive.mutiny.Mutiny.SessionFactory;
import static org.hibernate.reactive.mutiny.Mutiny.fetch;

import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Component
@AllArgsConstructor
public class BookRepositoryImpl implements BookRepository {

	private final SessionFactory sessionFactory;

	@Override
	public Uni<Book> findByIsbn(String isbn) {
		CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();
		CriteriaQuery<Book> query = criteriaBuilder.createQuery(Book.class);

		Root<Book> root = query.from(Book.class);
		query.where(criteriaBuilder.equal(root.get("isbn"), isbn));

		return sessionFactory.withSession(session -> session
			.createQuery(query)
			.getSingleResult()
			.onItem()
			.call(book -> fetch(book.getPages())));
	}
}
