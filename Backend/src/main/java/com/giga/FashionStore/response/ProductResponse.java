package com.giga.FashionStore.response;

import com.giga.FashionStore.model.Comment;

import java.util.List;

/**
 * Single product response model
 */
public class ProductResponse {
    private String prod_id;
    private String prodName;
    private String prodDescription;
    private String prodPrice;
    private List<Comment> prodComments;
    private double averageRating;

    public ProductResponse(String prod_id, String prodName, String prodDescription, String prodPrice, List<Comment> prodComments, double averageRating) {
        this.prod_id = prod_id;
        this.prodName = prodName;
        this.prodDescription = prodDescription;
        this.prodPrice = prodPrice;
        this.prodComments = prodComments;
        this.averageRating = averageRating;
    }

    // getters & setters
    public String getProd_id() {
        return prod_id;
    }

    public void setProd_id(String prod_id) {
        this.prod_id = prod_id;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdDescription() {
        return prodDescription;
    }

    public void setProdDescription(String prodDescriptio) {
        this.prodDescription = prodDescriptio;
    }

    public String getProdPrice() {
        return prodPrice;
    }

    public void setProdPrice(String prodPrice) {
        this.prodPrice = prodPrice;
    }

    public double getAverageRating() {
        return averageRating;
    }

    public void setAverageRating(double averageRating) {
        this.averageRating = averageRating;
    }

    public List<Comment> getProdComments() {
        return prodComments;
    }

    public void setProdComments(List<Comment> prodComments) {
        this.prodComments = prodComments;
    }
}
