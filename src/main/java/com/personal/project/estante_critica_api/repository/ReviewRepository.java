package com.personal.project.estante_critica_api.repository;


import com.personal.project.estante_critica_api.model.Review;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends MongoRepository<Review, String> {

    List<Review> findByBookId(String bookId);
    Boolean existsByBookIdAndUserId(String bookId, String userId);

}
