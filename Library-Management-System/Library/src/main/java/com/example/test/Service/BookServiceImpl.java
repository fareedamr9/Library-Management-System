package com.example.test.Service;

import com.example.test.DTOs.BookDTO;
import com.example.test.Entity.Author;
import com.example.test.Entity.Book;
import com.example.test.Entity.Category;
import com.example.test.Repository.AuthorRepository;
import com.example.test.Repository.BookRepository;
import com.example.test.Repository.CategoryRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }


    @Override
    public BookDTO updateBook(Long id, BookDTO book) {
        Optional<Book> bookOptional = bookRepository.findById(id);

        if (bookOptional.isPresent()) {
            Book existingBook = bookOptional.get();
            existingBook.setTitle(book.getTitle());
            existingBook.setAuthor(authorRepository.findById(book.getAuthorId()).orElse(null));
            existingBook.setCategory(categoryRepository.findById(book.getCategoryId()).orElse(null));
            return convertToDTO(bookRepository.save(existingBook));
        }
        else {
            throw new EntityNotFoundException("Book with id " + id + " not found");
        }
    }

    @Override
    public Optional<Book> findBookById(Long id) {
       return bookRepository.findById(id);
    }

    @Override
    public void saveBook(BookDTO bookDTO) {

//        Author author = authorRepository.findById(authorId)
//                .orElseThrow(() -> new RuntimeException("Author not found"));
//        Category category = categoryRepository.findById(categoryId)
//                .orElseThrow(() -> new RuntimeException("Category not found"));

        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        // Set author and category based on IDs if needed
        book.setAuthor(authorRepository.findById(bookDTO.getAuthorId()).orElse(null));
        book.setCategory(categoryRepository.findById(bookDTO.getCategoryId()).orElse(null));
        bookRepository.save(book);

    }

    @Override
    public Optional<List<Book>> findBookByTitle(String title) {
        return bookRepository.findByTitle(title);
//        return Optional.empty();
    }

    @Override
    public List<Book> findBookByAuthor(String author) {
        return bookRepository.findByAuthor_AuthorName(author);
    }

    @Override
    public List<Book> findBookByCategory(String categoryName) {
        return bookRepository.findByCategory_CategoryName(categoryName);
    }

    @Override
    public List<Book> findBookByAuthorAndCategory(String author,String categoryName) {
        return bookRepository.findByAuthor_AuthorNameAndCategory_CategoryName(author, categoryName);
    }
    private BookDTO convertToDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setTitle(book.getTitle());
        dto.setAuthorId(book.getAuthor() != null ? book.getAuthor().getId() : null);
        dto.setCategoryId(book.getCategory() != null ? book.getCategory().getId() : null);
        return dto;
    }

}
