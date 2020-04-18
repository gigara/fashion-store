package com.giga.FashionStore.repository;

import com.giga.FashionStore.model.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Rating repository to communicate with the DB
 */
public interface RatingRepository extends MongoRepository<Rating, String> {
}
