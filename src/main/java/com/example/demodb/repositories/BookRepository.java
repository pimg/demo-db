package com.example.demodb.repositories;

import com.example.demodb.domains.Book;
import io.smallrye.mutiny.Uni;

public interface BookRepository  {

	Uni<Book> findByIsbn(String isbn);

}
