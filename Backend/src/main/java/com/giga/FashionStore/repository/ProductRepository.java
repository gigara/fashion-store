package com.giga.FashionStore.repository;

import com.giga.FashionStore.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Product repository to communicate with the DB
 */
public interface ProductRepository extends MongoRepository<Product, Long> {
}
