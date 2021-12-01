package com.example.demodb.repositories;

import com.example.demodb.domains.Book;
import com.example.demodb.domains.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PageRepository extends CrudRepository<Page, Long> {

	List<Page> findByBook(Book book, Sort sort);
}
