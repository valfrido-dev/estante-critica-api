package com.personal.project.estante_critica_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    private static final String MESSAGE_DEFAULT = "Usuário não localizado. %s";
    public UserNotFoundException(String message) {
        super(String.format(MESSAGE_DEFAULT, message));
    }

}
