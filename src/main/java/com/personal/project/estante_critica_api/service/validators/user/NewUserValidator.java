package com.personal.project.estante_critica_api.service.validators.user;

import com.personal.project.estante_critica_api.endpoints.dto.UserDTO;
import com.personal.project.estante_critica_api.exceptions.UserAlreadyExistsException;
import com.personal.project.estante_critica_api.exceptions.UserValidationException;
import com.personal.project.estante_critica_api.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.regex.Matcher;

@Component
@RequiredArgsConstructor
@Slf4j
public class NewUserValidator implements UserValidatorImpl {

    private final UserRepository repository;

    @Override
    public void validator(UserDTO user) {
        Matcher matcher = patternPass.matcher(user.password());
        if (!matcher.find()) {
            throw new UserValidationException("A Senha não atende os requisitos necessários de segurança," +
                    " a senha deve conter caracter maiúsculo, minúsculo e um digito!");
        }

        boolean isExistUser = repository.existsByNameOrEmail(user.username(), user.email());
        if (isExistUser) {
            throw new UserAlreadyExistsException("O usuário informado já existe!");
        }
    }
}
