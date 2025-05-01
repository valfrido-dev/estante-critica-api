package com.personal.project.estante_critica_api.service;

import com.personal.project.estante_critica_api.endpoints.dto.user.NewUserDTO;
import com.personal.project.estante_critica_api.exceptions.UserNotFoundException;
import com.personal.project.estante_critica_api.model.User;
import com.personal.project.estante_critica_api.repository.UserRepository;
import com.personal.project.estante_critica_api.service.validators.user.UserValidatorImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {

    private final UserRepository repository;
    private final List<UserValidatorImpl<NewUserDTO>> validatorsDTO;
    private final List<UserValidatorImpl<User>> validators;

    @Transactional
    public User registerNewUser(NewUserDTO user) {
        validatorsDTO.forEach(v -> v.validator(user));
        String encriptedPass = this.encryptPassword(user.password());
        User newUser = new User(user.username(), user.name(), user.email(), encriptedPass);
        newUser.setCreateDate(LocalDateTime.now());
        newUser.setUpdateDate(LocalDateTime.now());
        newUser.setAdmin(Boolean.FALSE);
        newUser.setBooks(Collections.emptyList());
        return repository.save(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findByUsername(username);
        validators.forEach(v -> v.validator(user));
        return user;
    }

    public List<User> listUsers() {
        return repository.findAll();
    }

    @Transactional
    public User alterRoleUser(String userId, Boolean userAdmin) {
        var optionalUser = repository.findById(userId);
        var user = optionalUser.orElseThrow(() -> new UserNotFoundException("Usuário não localizado!"));
        user.setAdmin(userAdmin);
        user.setUpdateDate(LocalDateTime.now());
        return repository.save(user);
    }

    public Optional<User> getUserAutenticated() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if ( auth != null ) {
            return Optional.of((User) auth.getPrincipal());
        }
        return Optional.empty();
    }

    public Optional<User> getUsrById(String userId) {
        return repository.findById(userId);
    }

    private String encryptPassword(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }

}
