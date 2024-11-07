package com.javafullstackguru.repository;

import com.javafullstackguru.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
}
