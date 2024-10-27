package com.example.test.Controller;


import com.example.test.Entity.Author;
import com.example.test.Entity.Book;
import com.example.test.Service.AuthorService;
import com.example.test.Service.BookService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;



    @GetMapping

    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBookById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable("id") Long id) {
       Optional<Book> book= bookService.findBookById(id);
        return ResponseEntity.ok(book.get());
    }

    @PostMapping("book/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable ("id") Long id,@RequestBody Book book) {
        try {
            Book updatedBook = bookService.updateBook(id, book);
            return ResponseEntity.ok(updatedBook);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PostMapping
     public ResponseEntity<String> CreateBook(@RequestBody Book book) {
        bookService.saveBook(book);
        return ResponseEntity.ok().body(book.toString());
    }

    @GetMapping("/search/title")
    public ResponseEntity< Optional<List<Book>>> getBooksByTitle(@RequestParam String title) {
        Optional<List<Book>> books= bookService.findBookByTitle(title);
        if(books.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(books);
    }

    @GetMapping("/search/author")
    public ResponseEntity<List<Book>> getBooksByAuthor(@RequestParam String authorName) {
         List<Book> books = bookService.findBookByAuthor(authorName);
        if(books.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(books);
    }

    @GetMapping("/search/category")
    public ResponseEntity<List<Book>> getBooksByCategory(@RequestParam String categoryName) {
        List<Book> books = bookService.findBookByCategory(categoryName);
        if(books.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(books);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> getBooksByCategoryAndAuthor( @RequestParam String authorName,@RequestParam String categoryName) {
        List<Book> books = bookService.findBookByAuthorAndCategory(authorName,categoryName);
        if(books.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok().body(books);
    }




}
