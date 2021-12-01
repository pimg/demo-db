package com.example.demodb.domains;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "books")
@NoArgsConstructor
@Getter
@Setter
public class Book implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String author;

	@Column(unique = true)
	private String isbn;

	@OneToMany(mappedBy = "book", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<Page> pages;

	public Book(String title, String author, String isbn) {
		this.title = title;
		this.author = author;
		this.isbn = isbn;
	}

}
