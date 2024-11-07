package com.javafullstackguru.service;

import com.javafullstackguru.entity.Book;
import com.javafullstackguru.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepo;

    @Autowired // Inject BookRepository using constructor injection
    public BookServiceImpl(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepo.save(book);
    }

    @Override
    public Book getBookById(Integer id) {
        return bookRepo.findById(id).orElse(null); // Handle case where book with given ID is not found
    }

    @Override
    public Book updateBook(Book book) {
        return bookRepo.save(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookRepo.deleteById(id);
    }
}
