package com.example.demodb.repositories.impl;

import com.example.demodb.repositories.entities.Book;
import com.example.demodb.repositories.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import static io.smallrye.mutiny.converters.uni.UniReactorConverters.toMono;
import static org.hibernate.reactive.mutiny.Mutiny.SessionFactory;
import static org.hibernate.reactive.mutiny.Mutiny.fetch;

@Component
@AllArgsConstructor
public class BookRepositoryImpl implements BookRepository {

	private final SessionFactory sessionFactory;

	@Override
	public Mono<Book> findByIsbn(String isbn) {
		return sessionFactory.withSession(session -> session
				.createQuery(buildFindBookByIsbnQuery(isbn))
				.getSingleResult()
				.onItem()
				.call(book -> fetch(book.getPages()))
			)
			.convert()
			.with(toMono());
	}

	@Override
	public Mono<Book> findSummaryByIsbn(String isbn) {
		return sessionFactory.withSession(session -> session
				.createQuery(buildFindBookByIsbnQuery(isbn))
				.getSingleResult()
			)
			.convert()
			.with(toMono());
	}

	private CriteriaQuery<Book> buildFindBookByIsbnQuery(String isbn) {
		CriteriaBuilder criteriaBuilder = sessionFactory.getCriteriaBuilder();
		CriteriaQuery<Book> query = criteriaBuilder.createQuery(Book.class);

		Root<Book> root = query.from(Book.class);
		query.where(criteriaBuilder.equal(root.get("isbn"), isbn));
		return query;
	}
}
