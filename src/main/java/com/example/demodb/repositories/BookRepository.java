package com.example.demodb.repositories;

import com.example.demodb.domains.Book;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;


public interface BookRepository extends ReactiveCrudRepository<Book, Long> {


	Flux<Book> findByIsbn(String isbn);
}
