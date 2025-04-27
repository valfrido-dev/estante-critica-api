package com.personal.project.estante_critica_api.service.validators.user;

import java.util.regex.Pattern;

public interface UserValidatorImpl<T> {
    Pattern patternPass =
            Pattern.compile("(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z0-9]{6,8}$");

    void validator(T user);

}
