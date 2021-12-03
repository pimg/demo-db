package com.example.demodb.domains;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.Set;

@Table("books")
@Builder
@Value
public class Book {

	@Id
	private Long id;
	private String title;
	private String author;
	private String isbn;
	private Set<Page> pages;


}
