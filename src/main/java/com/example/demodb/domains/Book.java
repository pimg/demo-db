package com.example.demodb.domains;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@SuperBuilder
@Getter
public class Book extends BookSummary {

	private final Set<Page> pages;

}
