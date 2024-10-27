package com.example.test.Controller;

import com.example.test.Entity.Author;
import com.example.test.Entity.Category;
import com.example.test.Service.CategoryService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @PostMapping("category/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable("id") Long id, @RequestBody Category category) {
        try {
            Category savedCategory = categoryService.update(id, category);
            return ResponseEntity.ok(savedCategory);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable("id") Long id) {
        categoryService.delete(id);
        return ResponseEntity.ok().build();
    }
    @PostMapping
    public ResponseEntity<Category> CreateCategory(@RequestBody Category category) {
        Category savedCategory= categoryService.save(category);
        return ResponseEntity.status(201).body(savedCategory);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable("id") Long id) {
        Optional<Category> category= categoryService.findById(id);
        return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}
