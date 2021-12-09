package com.example.demodb.controller;

import com.example.demodb.domains.Book;
import com.example.demodb.domains.Page;
import com.example.demodb.repositories.BookRepository;
import com.example.demodb.repositories.PageRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashSet;
import java.util.Set;

@RestController
@AllArgsConstructor
public class BookController {

	private final BookRepository bookRepository;
	private final PageRepository pageRepository;

	@GetMapping("/books/{isbn}")
	public Flux<Book> findBookByIbsn(@PathVariable String isbn) {
		return bookRepository.findByIsbn(isbn)
			.flatMap(book ->
				Mono.zip(pageRepository.findByBookId(book.getId())
					.collectList(), Mono.just(book))
			).map(objects -> {
				Set<Page> pageSet = new HashSet<>(objects.getT1());
				Book book = objects.getT2();
				return Book.builder()
					.pages(pageSet)
					.id(book.getId())
					.title(book.getTitle())
					.isbn(book.getIsbn())
					.author(book.getAuthor())
					.build();
			});
	}
}
