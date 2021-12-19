package com.example.demodb.service;

import com.example.demodb.domains.Book;
import com.example.demodb.domains.BookSummary;
import reactor.core.publisher.Mono;

public interface BookService {
	Mono<BookSummary> findBookSummaryByIsbn(String isbn);
	Mono<Book> findBookByIsbn(String isbn);
}
