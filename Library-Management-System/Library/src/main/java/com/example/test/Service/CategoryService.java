package com.example.test.Service;

import com.example.test.Entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();
    Optional<Category> findById(Long id);
    Category save(Category category);
    void delete(Long id);
    Category update(Long id,Category category);
}
