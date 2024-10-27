package com.example.test.Service;

import com.example.test.Entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorService {

    List<Author> findAll();
    Author saveAuthor(Author author);
    Optional< Author> findAuthorById(Long id);
    void deleteAuthorById(Long id);
    Author updateAuthor(Long id, Author author);
}
