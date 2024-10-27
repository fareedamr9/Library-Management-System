package com.example.test.Service;

import com.example.test.Entity.Author;
import com.example.test.Repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public List<Author> findAll() {
       return authorRepository.findAll();
    }

    @Override
    public Author saveAuthor(Author author) {
       return authorRepository.save(author);
    }

    @Override
    public Optional< Author> findAuthorById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public void deleteAuthorById(Long id) {
        authorRepository.deleteById(id);
    }

    @Override
    public Author updateAuthor(Long id, Author author) {
        Optional<Author> authorOptional = findAuthorById(id);

        if (authorOptional.isPresent()) {
            Author existingAuthor = authorOptional.get();
            existingAuthor.setAuthorName(author.getAuthorName());
            return authorRepository.save(existingAuthor);
        }
        else throw new EntityNotFoundException("Author with id " + id + " not found");
    }


}
