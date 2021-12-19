package com.example.demodb.controller;

import com.example.demodb.domains.Book;
import com.example.demodb.domains.BookSummary;
import com.example.demodb.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
public class BookController {

	private final BookService bookService;

	@GetMapping("/books/{isbn}")
	public Mono<Book> findBookByIbsn(@PathVariable String isbn) {
		return bookService.findBookByIsbn(isbn);
	}

	@GetMapping("/book-summaries/{isbn}")
	public Mono<BookSummary> findBookByIsbn(@PathVariable String isbn) {
		return bookService.findBookSummaryByIsbn(isbn);
	}
}
