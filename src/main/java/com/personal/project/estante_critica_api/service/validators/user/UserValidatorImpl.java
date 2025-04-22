package com.personal.project.estante_critica_api.service.validators.user;

import com.personal.project.estante_critica_api.endpoints.dto.UserDTO;

import java.util.regex.Pattern;

public interface UserValidatorImpl {
    Pattern patternPass =
            Pattern.compile("(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z0-9]{6,8}$");

    void validator(UserDTO user);

}
