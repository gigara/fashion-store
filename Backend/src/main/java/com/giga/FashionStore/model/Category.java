package com.giga.FashionStore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Product category model.
 */
@Document(collection = "categories")
public class Category {
    public static final String SEQUENCE_NAME = "category_sequence";

    @Id
    private String categoryId;
    private String categoryName;

    public Category(String categoryId, String categoryName) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
    }

    // getters
    public String getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    // setters
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
