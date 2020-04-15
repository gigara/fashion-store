package com.giga.FashionStore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Product discount model.
 */
@Document(collection = "discounts")
public class Discount {
    @Id
    private long discount_id;
    private String discount_name;
    private int discount_value;

    public Discount(String discount_name, int discount_value) {
        this.discount_name = discount_name;
        this.discount_value = discount_value;
    }

    // getters
    public long getDiscount_id() {
        return discount_id;
    }

    public String getDiscount_name() {
        return discount_name;
    }

    public int getDiscount_value() {
        return discount_value;
    }

    // setters
    public void setDiscount_id(long discount_id) {
        this.discount_id = discount_id;
    }

    public void setDiscount_name(String discount_name) {
        this.discount_name = discount_name;
    }

    public void setDiscount_value(int discount_value) {
        this.discount_value = discount_value;
    }
}
