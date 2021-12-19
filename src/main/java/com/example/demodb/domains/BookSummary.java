package com.example.demodb.domains;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Getter
public class BookSummary {

	private final String title;
	private final String author;
	private final String isbn;
}
