package com.example.demodb.controller;

import com.example.demodb.domains.Book;
import com.example.demodb.repositories.BookRepository;
import com.example.demodb.repositories.PageRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@AllArgsConstructor
public class BookController {

	private final BookRepository bookRepository;
	private final PageRepository pageRepository;

	@GetMapping("/books/{isbn}")
	public Mono<Book> findBookByIbsn(@PathVariable String isbn) {
		return Mono.fromCallable(() -> bookRepository.findByIsbn(isbn)).subscribeOn(Schedulers.boundedElastic());
	}
}
