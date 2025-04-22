package com.personal.project.estante_critica_api.repository;

import com.personal.project.estante_critica_api.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {

    @Query(value="{$or :[{title: ?0}, {authors: [?1]}, {category: ?2}, {publisher: ?3}]}", sort="{numberAverageRating:-1}")
    List<Book> findByParams(String title, String author, String category, String publisher);

}
