package com.giga.FashionStore.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Product rating model.
 */
@Document(collection = "ratings")
public class Rating {
    @Id
    private String rating_id;
    private double rating_rate;
    private User user;

    public Rating(double rating_rate, User user) {
        this.rating_rate = rating_rate;
        this.user = user;
    }

    // getters
    public String getRating_id() {
        return rating_id;
    }

    public double getRating_rate() {
        return rating_rate;
    }

    public User getUser() {
        return user;
    }

    // setters
    public void setRating_id(String rating_id) {
        this.rating_id = rating_id;
    }

    public void setRating_rate(double rating_rate) {
        this.rating_rate = rating_rate;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
