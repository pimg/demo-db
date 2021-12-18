package com.example.demodb.repositories;

import com.example.demodb.domains.Book;
import reactor.core.publisher.Mono;

public interface BookRepository  {

	Mono<Book> findByIsbn(String isbn);

}
