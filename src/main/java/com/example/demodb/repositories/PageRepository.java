package com.example.demodb.repositories;

import com.example.demodb.domains.Book;
import com.example.demodb.domains.Page;

import java.util.List;

public interface PageRepository  {

	List<Page> findByBook(Book book);
}
