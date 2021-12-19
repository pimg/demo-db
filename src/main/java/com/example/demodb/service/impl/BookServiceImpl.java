package com.example.demodb.service.impl;

import com.example.demodb.domains.Book;
import com.example.demodb.domains.BookSummary;
import com.example.demodb.domains.Page;
import com.example.demodb.repositories.BookRepository;
import com.example.demodb.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;

	@Override
	public Mono<BookSummary> findBookSummaryByIsbn(String isbn) {
		return bookRepository.findSummaryByIsbn(isbn).map(book ->
				BookSummary.builder()
					.isbn(book.getIsbn())
					.title(book.getTitle())
					.author(book.getAuthor())
					.build()
			);
	}

	@Override
	public Mono<Book> findBookByIsbn(String isbn) {

		return bookRepository.findByIsbn(isbn).map(book -> {

				Set<Page> pages = book.getPages().stream().map(page ->
					Page.builder()
						.number(page.getNumber())
						.content(page.getContent())
						.chapter(page.getChapter())
						.build())
					.collect(Collectors.toSet());
				return Book.builder()
					.author(book.getAuthor())
					.isbn(book.getIsbn())
					.title(book.getTitle())
					.pages(pages)
					.build();
			}
		);
	}
}
