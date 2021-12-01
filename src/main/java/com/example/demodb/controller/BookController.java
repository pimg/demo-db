package com.example.demodb.controller;

import com.example.demodb.domains.Book;
import com.example.demodb.repositories.BookRepository;
import com.example.demodb.repositories.PageRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class BookController {

	private final BookRepository bookRepository;
	private final PageRepository pageRepository;

	@GetMapping("/books/{ibsn}")
	public Book findBookByIbsn(@PathVariable String ibsn) {
		return bookRepository.findByIsbn(ibsn);
	}
}
