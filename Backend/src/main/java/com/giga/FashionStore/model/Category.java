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
    private long categoryId;
    private String categoryName;

    public Category(long category_id, String category_name) {
        this.categoryId = category_id;
        this.categoryName = category_name;
    }

    // getters
    public long getCategoryId() {
        return categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    // setters
    public void setCategoryId(long categoryId) {
        this.categoryId = categoryId;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
