package com.example.demodb.persistence.repositories;

import com.example.demodb.persistence.entities.Book;
import reactor.core.publisher.Mono;

public interface BookRepository  {

	Mono<Book> findByIsbn(String isbn);
	Mono<Book> findSummaryByIsbn(String isbn);

}
