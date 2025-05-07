package com.personal.project.estante_critica_api.repository;

import com.personal.project.estante_critica_api.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    Boolean existsByTitleAndPublisher(String title, String publisher);

}
