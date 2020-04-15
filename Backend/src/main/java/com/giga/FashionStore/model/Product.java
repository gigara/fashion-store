package com.giga.FashionStore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Product model.
 */
@Document(collection = "products")
public class Product {
    public static final String SEQUENCE_NAME = "product_sequence";

    @Id
    private long prod_id;
    private String prodName;
    private String prodDescription;
    private String prodPrice;
    @DBRef
    private Category prodCategory;
    @DBRef
    private Discount prodDiscount;
    @DBRef
    private List<Comment> prodComments;
    @DBRef
    private List<Rating> prodRatings;

    public Product(long prod_id, String prodName, String prodDescription, String prodPrice) {
        this.prod_id = prod_id;
        this.prodName = prodName;
        this.prodDescription = prodDescription;
        this.prodPrice = prodPrice;
    }

    // getters
    public long getProd_id() {
        return prod_id;
    }

    public String getProdName() {
        return prodName;
    }

    public String getProdDescription() {
        return prodDescription;
    }

    public String getProdPrice() {
        return prodPrice;
    }

    public Category getProdCategory() {
        return prodCategory;
    }

    public Discount getProdDiscount() {
        return prodDiscount;
    }

    public List<Comment> getProdComments() {
        return prodComments;
    }

    public List<Rating> getProdRatings() {
        return prodRatings;
    }

    // setters
    public void setProd_id(long prod_id) {
        this.prod_id = prod_id;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public void setProdDescription(String prodDescription) {
        this.prodDescription = prodDescription;
    }

    public void setProdPrice(String prodPrice) {
        this.prodPrice = prodPrice;
    }

    public void setProdCategory(Category prodCategory) {
        this.prodCategory = prodCategory;
    }

    public void setProdDiscount(Discount prodDiscount) {
        this.prodDiscount = prodDiscount;
    }

    public void setProdComments(List<Comment> prodComments) {
        this.prodComments = prodComments;
    }

    public void setProdRatings(List<Rating> prodRatings) {
        this.prodRatings = prodRatings;
    }
}
