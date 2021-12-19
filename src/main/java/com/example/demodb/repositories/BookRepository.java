package com.example.demodb.repositories;

import com.example.demodb.repositories.entities.Book;
import reactor.core.publisher.Mono;

public interface BookRepository  {

	Mono<Book> findByIsbn(String isbn);
	Mono<Book> findSummaryByIsbn(String isbn);

}
