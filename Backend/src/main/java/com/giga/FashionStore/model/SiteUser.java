package com.giga.FashionStore.model;

import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Site user class that extends the abstract user class.
 */
@Document(collection = "User")
@TypeAlias("SiteUser")
public class SiteUser extends User {

    public SiteUser(String userID, String firstName, String lastName, String username, String email, String password) {
        super(userID, firstName, lastName, username, email, password);
    }
}
