package com.example.demodb;

import com.example.demodb.domains.Book;
import com.example.demodb.domains.Page;
import com.example.demodb.repositories.BookRepository;
import com.example.demodb.repositories.PageRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DemoDbApplication {


	public static void main(String[] args) {
		SpringApplication.run(DemoDbApplication.class, args);
	}

	@Bean
	public CommandLineRunner mappingDemo(BookRepository bookRepository, PageRepository pageRepository) {
		return args -> {
			Book book = new Book("Java 101", "John Doe", "123456");

			bookRepository.save(book);

			pageRepository.save(new Page(1, "Introduction contents", "Introduction", book));
			pageRepository.save(new Page(65, "Java 8 contents", "Java 8", book));
			pageRepository.save(new Page(95, "Concurrency contents", "Concurrency", book));

			Book bookresult = bookRepository.findByIsbn("123456");
			System.out.println("number of pages in book: "+bookresult.getPages().size());
		};
	}

}
