package com.giga.FashionStore.response;

import com.giga.FashionStore.model.Category;

public class ProductsResponse {
    private long _id;
    private String prodName;
    private String prodPrice;
    private Category prodCategory;

    public ProductsResponse(long _id, String prodName, String prodPrice, Category prodCategory) {
        this._id = _id;
        this.prodName = prodName;
        this.prodPrice = prodPrice;
        this.prodCategory = prodCategory;
    }

    // getters
    public long get_id() {
        return _id;
    }

    public String getProdName() {
        return prodName;
    }

    public String getProdPrice() {
        return prodPrice;
    }

    public Category getProdCategory() {
        return prodCategory;
    }

    // setters
    public void set_id(long _id) {
        this._id = _id;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public void setProdPrice(String prodPrice) {
        this.prodPrice = prodPrice;
    }

    public void setProdCategory(Category prodCategory) {
        this.prodCategory = prodCategory;
    }
}
