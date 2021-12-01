package com.example.demodb.repositories;

import com.example.demodb.domains.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {

	Book findByIsbn(String isbn);

}
