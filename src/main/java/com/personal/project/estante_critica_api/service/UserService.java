package com.personal.project.estante_critica_api.service;

import com.personal.project.estante_critica_api.endpoints.dto.UserDTO;
import com.personal.project.estante_critica_api.model.User;
import com.personal.project.estante_critica_api.repository.UserRepository;
import com.personal.project.estante_critica_api.service.validators.user.UserValidatorImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {

    private final UserRepository repository;
    private final List<UserValidatorImpl> validators;

    public User registerNewUser(UserDTO user) {
        validators.forEach(v -> v.validator(user));
        User newUser = new User(user.username(), user.email(), user.password());
        newUser.setCreateDate(LocalDateTime.now());
        newUser.setUpdateDate(LocalDateTime.now());
        newUser.setIsAdmin(Boolean.FALSE);
        newUser.setBooks(Collections.emptyList());
        return repository.save(newUser);
    }


}
