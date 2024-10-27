package com.example.test.Controller;


import com.example.test.Entity.Author;
import com.example.test.Entity.Book;
import com.example.test.Service.AuthorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @GetMapping
    public List<Author> getAuthors() {
        return authorService.findAll();
    }
    @PostMapping
    public ResponseEntity<Author> CreateAuthor(@RequestBody Author author) {
       Author savedAuthor= authorService.saveAuthor(author);
        return ResponseEntity.status(201).body(savedAuthor);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Author> getBookById(@PathVariable("id") Long id) {
        Optional<Author> author= authorService.findAuthorById(id);
        return author.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") Long id) {
        authorService.deleteAuthorById(id);
        return ResponseEntity.noContent().build();
    }
    @PostMapping("author/{id}")
    public ResponseEntity<Author> updateBook(@PathVariable ("id") Long id,@RequestBody Author author) {
        try {
            Author savedAuthor = authorService.updateAuthor(id, author);
            return ResponseEntity.ok(savedAuthor);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
