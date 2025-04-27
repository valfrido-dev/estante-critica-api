package com.personal.project.estante_critica_api.service.validators.user;


import com.personal.project.estante_critica_api.exceptions.UserNotFoundException;
import com.personal.project.estante_critica_api.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserValidator implements UserValidatorImpl<User> {

    @Override
    public void validator(User user) {
        if (user == null) {
            throw new UserNotFoundException("Usuário não localizado!");
        }
    }
}
