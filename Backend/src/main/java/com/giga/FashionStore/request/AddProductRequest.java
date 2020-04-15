package com.giga.FashionStore.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * Add product request object class.
 */
public class AddProductRequest {
    @NotBlank
    @Size(min = 3, max = 100)
    private String prodName;

    @NotBlank
    @Size(min = 3, max = 1000)
    private String prodDescription;

    @NotBlank
    @Size(min = 3, max = 10)
    private String prodPrice;

    private long prodCategory;

    // getters
    public String getProdName() {
        return prodName;
    }

    public String getProdDescription() {
        return prodDescription;
    }

    public String getProdPrice() {
        return prodPrice;
    }

    public long getProdCategory() {
        return prodCategory;
    }

    // setters
    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public void setProdDescription(String prodDescription) {
        this.prodDescription = prodDescription;
    }

    public void setProdPrice(String prodPrice) {
        this.prodPrice = prodPrice;
    }

    public void setProdCategory(long prodCategory) {
        this.prodCategory = prodCategory;
    }
}
