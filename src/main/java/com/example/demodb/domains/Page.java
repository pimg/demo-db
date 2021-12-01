package com.example.demodb.domains;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "pages")
@NoArgsConstructor
@Getter
@Setter
public class Page implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private int number;
	private String content;
	private String chapter;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "book_id", nullable = false)
	@JsonIgnore
	private Book book;

	public Page(int number, String content, String chapter, Book book) {
		this.number = number;
		this.content = content;
		this.chapter = chapter;
		this.book = book;
	}

}
