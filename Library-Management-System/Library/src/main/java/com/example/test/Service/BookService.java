package com.example.test.Service;

import com.example.test.Entity.Book;

import java.util.List;
import java.util.Optional;


public interface BookService {

    List<Book> getAllBooks();


    void deleteBookById(Long id);

    Book updateBook(Long id, Book book);

    Optional<Book> findBookById(Long id);

    void saveBook(Book book);

    Optional<List<Book>> findBookByTitle(String title);

    List<Book> findBookByAuthor(String author);

    List<Book> findBookByCategory(String categoryName);

    List<Book> findBookByAuthorAndCategory(String author, String categoryName);
}