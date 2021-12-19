package com.example.demodb.domains;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Page {

	private final int number;
	private final String content;
	private final  String chapter;
}
