package com.giga.FashionStore.model;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Store manager user class that extends the abstract user class.
 */
@Document(collection = "User")
@TypeAlias("StoreManager")
public class StoreManager extends User {
    public StoreManager(String userID, String firstName, String lastName, String username, String email, String password) {
        super(userID, firstName, lastName, username, email, password);
    }
}
