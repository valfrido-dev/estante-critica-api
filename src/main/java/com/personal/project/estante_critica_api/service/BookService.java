package com.personal.project.estante_critica_api.service;


import com.personal.project.estante_critica_api.exceptions.ResourceNotFoundException;
import com.personal.project.estante_critica_api.model.Book;
import com.personal.project.estante_critica_api.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookService {

    private final BookRepository repository;
    private final MongoTemplate mongoTemplate;

    public List<Book> listBooks() {

        log.info("iniciar lista de livros:::");
        Sort sortAverageRating = this.getSortedAverageRatingDesc();
        return repository.findAll(sortAverageRating);

    }

    public List<Book> findByParams(String title, String author,
                             String category, String publisher) {
        Query query = new Query();
        List<Criteria> criteriaList = new ArrayList<>();
        if (title != null && !title.isBlank()){
            criteriaList.add(Criteria.where("title").regex(".*" + title + ".*"));
        }
        if (category != null && !category.isBlank()){
            criteriaList.add(Criteria.where("category").is(category));
        }
        if (publisher != null && !publisher.isBlank()){
            criteriaList.add(Criteria.where("publisher").regex(".*" + publisher + ".*"));
        }
        if (author != null && !author.isBlank()){
            criteriaList.add(Criteria.where("authors").is(author));
        }
        if (!criteriaList.isEmpty()){
            query.addCriteria(new Criteria().andOperator(criteriaList.toArray(new Criteria[0])));
        }
        query.with(this.getSortedAverageRatingDesc());
        return mongoTemplate.find(query, Book.class);
    }

//    public List<Book> findByParams(String title, String author,
//                                   String category, String publisher) {
//       Example<Book> exampleSearch = this.getBookExampleSearch(title, author, category, publisher);
//       Sort sortAverageRating = this.getSortedAverageRatingDesc();
//        return repository.findAll(exampleSearch, sortAverageRating);
//    }
//
//    private Example<Book> getBookExampleSearch(String title, String author, String category, String publisher) {
//        Book bookSearch = new Book();
//        bookSearch.setTitle(title);
//        bookSearch.setAuthors(author != null ? List.of(author) : null);
//        bookSearch.setCategory(category);
//        bookSearch.setPublisher(publisher);
//
//        ExampleMatcher matcherNullable = ExampleMatcher.matching()
//                .withIgnoreNullValues()
//                .withMatcher("title", ExampleMatcher.GenericPropertyMatchers.contains())
//                .withMatcher("authors", ExampleMatcher.GenericPropertyMatchers.contains())
//                .withMatcher("category", ExampleMatcher.GenericPropertyMatchers.exact())
//                .withMatcher("publisher", ExampleMatcher.GenericPropertyMatchers.contains());
//
//        return Example.of(bookSearch, matcherNullable);
//    }

    public Optional<Book> findById(String id) {
        return repository.findById(id);
    }

    private Sort getSortedAverageRatingDesc() {
       return Sort.by(Sort.Direction.DESC, "numberAverageRating");
    }

}
