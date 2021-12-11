package com.example.demodb.controller;

import com.example.demodb.domains.Book;
import com.example.demodb.repositories.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static io.smallrye.mutiny.converters.uni.UniReactorConverters.toMono;

@RestController
@AllArgsConstructor
public class BookController {

	private final BookRepository bookRepository;

	@GetMapping("/books/{isbn}")
	public Mono<Book> findBookByIbsn(@PathVariable String isbn) {
		return bookRepository.findByIsbn(isbn).convert().with(toMono());
	}
}
