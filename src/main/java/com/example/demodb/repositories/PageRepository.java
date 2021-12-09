package com.example.demodb.repositories;

import com.example.demodb.domains.Page;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface PageRepository extends ReactiveCrudRepository<Page, Long> {

	Flux<Page> findByBookId(Long bookId);
}
