package com.personal.project.estante_critica_api.repository;

import com.personal.project.estante_critica_api.model.Book;
import com.personal.project.estante_critica_api.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String>   {
    Boolean existsByNameOrEmail(String name, String email);
}
