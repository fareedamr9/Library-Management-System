package com.example.test.DTOs;

public class BookDTO {
    private String title;
    private Long authorId;  // Reference to the author's ID
    private Long categoryId; // Reference to the category's ID

    public BookDTO() {

    }

    public BookDTO( String title, Long authorId, Long categoryId) {
        this.title = title;
        this.authorId = authorId;
        this.categoryId = categoryId;
    }

    // Getters and Setters



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
