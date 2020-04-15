package com.giga.FashionStore.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreateProductCategoryRequest {
    @NotBlank
    @Size(min = 3, max = 40)
    private String categoryName;

    // getters
    public String getCategoryName() {
        return categoryName;
    }

    // setters
    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
