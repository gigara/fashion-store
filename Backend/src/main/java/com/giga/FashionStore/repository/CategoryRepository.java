package com.giga.FashionStore.repository;

import com.giga.FashionStore.model.Category;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

/**
 * Category repository communicate with the DB
 */
public interface CategoryRepository extends MongoRepository<Category, String> {
    @Query(value = "{categoryName : ?0}", exists = true)
    Boolean existsByCategoryName(String categoryName);
}
