package com.example.test.Repository;

import com.example.test.Entity.Author;
import com.example.test.Entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BookRepository extends JpaRepository <Book, Long> {

    public Optional<List<Book>> findByTitle(String title);
    public List<Book> findByAuthor_AuthorName(String author);
    public List<Book> findByCategory_CategoryName(String category);
    public List<Book> findByAuthor_AuthorNameAndCategory_CategoryName(String author, String category);
}
