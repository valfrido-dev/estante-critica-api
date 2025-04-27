package com.personal.project.estante_critica_api.repository;


import com.personal.project.estante_critica_api.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends MongoRepository<User, String>   {
    Boolean existsByUsernameOrEmail(String username, String email);
    User findByUsername(String username);
}
