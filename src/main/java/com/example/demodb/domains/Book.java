package com.example.demodb.domains;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Table("books")
@Builder
@Value
@AllArgsConstructor
public class Book {

	@Id
	private Long id;
	private String title;
	private String author;
	private String isbn;

	@Transient
	private Set<Page> pages;

	@PersistenceConstructor
	public Book(Long id, String title, String author, String isbn) {
		this.id = id;
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.pages = null;
	}
}
