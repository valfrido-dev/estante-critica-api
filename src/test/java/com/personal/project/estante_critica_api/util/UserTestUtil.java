package com.personal.project.estante_critica_api.util;

import com.personal.project.estante_critica_api.endpoints.dto.user.NewUserDTO;
import com.personal.project.estante_critica_api.endpoints.dto.user.RequestAutenticatorDTO;
import com.personal.project.estante_critica_api.endpoints.dto.user.UserDTO;
import com.personal.project.estante_critica_api.endpoints.dto.user.UserRoleDTO;
import com.personal.project.estante_critica_api.model.User;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class UserTestUtil {

    private static final String USER_NAME_TEST = "username";
    private static final String NAME_USER_TEST = "nome Usuario ";
    private static final String EMAIL_TEST = "@test.com.br";
    private static final String PASSWORD_TEST = "1234Adg";

    private UserTestUtil() {

    }

    public static User getUserResponse(Integer usernameComplement, Boolean isAdmin) {
        var username = getConcatInfoUser(USER_NAME_TEST, String.valueOf(usernameComplement));
        var nomeUsuario = getConcatInfoUser(NAME_USER_TEST, String.valueOf(usernameComplement));
        var email = getConcatInfoUser(USER_NAME_TEST, EMAIL_TEST);
        var user = new User(username,
                nomeUsuario,
                email,
                PASSWORD_TEST);
        user.setId(generatedUserId());
        user.setAdmin(isAdmin);
        return user;
    }

    public static NewUserDTO getNewUserRequisicao(Integer usernameComplement) {
        var username = getConcatInfoUser(USER_NAME_TEST, String.valueOf(usernameComplement));
        var nomeUsuario = getConcatInfoUser(NAME_USER_TEST, String.valueOf(usernameComplement));
        var email = getConcatInfoUser(USER_NAME_TEST, EMAIL_TEST);
        return new NewUserDTO(username,
                nomeUsuario,
                email,
                PASSWORD_TEST);
    }

    public static UserRoleDTO getUserRoleDTo(String userId, Boolean isAdmin) {
        return new UserRoleDTO(userId, isAdmin);
    }

    public static RequestAutenticatorDTO getRequestAutenticator(Integer usernameComplement) {
        var username = getConcatInfoUser(USER_NAME_TEST, String.valueOf(usernameComplement));
        return new RequestAutenticatorDTO(username, PASSWORD_TEST);
    }

    public static UserDTO getUserDTO(Integer usernameComplement, Boolean isAdmin) {
        var username = getConcatInfoUser(USER_NAME_TEST, String.valueOf(usernameComplement));
        var nomeUsuario = getConcatInfoUser(NAME_USER_TEST, String.valueOf(usernameComplement));
        var email = getConcatInfoUser(USER_NAME_TEST, EMAIL_TEST);
        return new UserDTO(
                generatedUserId(), username, nomeUsuario,
                email, isAdmin, List.of(),
                LocalDateTime.now(), LocalDateTime.now());
    }

    public static String generatedUserId() {
        return UUID.randomUUID().toString();
    }

    private static String getConcatInfoUser(String fieldConcat, String valueComplement) {
        return fieldConcat + valueComplement;
    }

}
