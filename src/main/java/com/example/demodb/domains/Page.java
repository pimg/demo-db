package com.example.demodb.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;


@Table("pages")
@Value
@Builder
public class Page {

	@JsonIgnore
	@Id
	private Long id;

	private int number;
	private String content;
	private String chapter;

	@JsonIgnore
	@Column("book_id")
	private Long bookId;

}
