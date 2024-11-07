package com.javafullstackguru.service;

import com.javafullstackguru.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book saveBook(Book book);
    Book getBookById(Integer id);
    Book updateBook(Book book);
    void deleteBookById(Integer id);
  
}
