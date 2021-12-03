package com.example.demodb.repositories;

import com.example.demodb.domains.Book;
import com.example.demodb.domains.Page;
import lombok.AllArgsConstructor;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.stream.Collectors;

@AllArgsConstructor
@Component
public class BookRepository {

	private final DatabaseClient databaseClient;

	public Flux<Book> findByIsbn(String isbn) {
		return databaseClient.sql("SELECT books.id, books.author, books.isbn, " +
				"books.title, pages.content, pages.chapter, pages.number " +
				"FROM public.books " +
				"LEFT OUTER JOIN pages ON (books.id = pages.book_id) " +
				"WHERE books.isbn = :isbn")
			.bind("isbn", isbn)
			.fetch()
			.all()
			.bufferUntilChanged(result -> result.get("isbn"))
			.map(bookresult -> Book.builder()
				.author(String.valueOf(bookresult.get(0).get("author")))
				.title(String.valueOf(bookresult.get(0).get("title")))
				.isbn(String.valueOf(bookresult.get(0).get("isbn")))
				.id((Long) bookresult.get(0).get("id"))
				.pages(bookresult.stream()
					.map(pageresult ->
						Page.builder()
							.number((Integer) (pageresult.get("number")))
							.bookId((Long) pageresult.get("id"))
							.content(String.valueOf(pageresult.get("content")))
							.chapter(String.valueOf(pageresult.get("chapter")))
							.build()
					).collect(Collectors.toSet()))
				.build());
	}
}
