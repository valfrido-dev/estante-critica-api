package com.personal.project.estante_critica_api.endpoints;


import com.personal.project.estante_critica_api.security.JwtUtilService;
import com.personal.project.estante_critica_api.service.UserService;
import com.personal.project.estante_critica_api.util.UserTestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UserControllerTest {

    @InjectMocks
    private UserController userController;
    @Mock
    private UserService userService;
    @Mock
    private AuthenticationManager authenticationManager;
    @Mock
    private JwtUtilService jwtUtilService;

    @Test
    @DisplayName("Teste cadastro usuaário - novo usuário - register user")
    void testNewUserRegister() {
        var requisicao = UserTestUtil.getNewUserRequisicao(1);
        var userRegistered = UserTestUtil.getUserResponse(1, false);
        var responseExpected = String.format(
                "Usuário incluído com sucesso! Bem vindo: %s!", userRegistered.getName());
        when(userService.registerNewUser(requisicao)).thenReturn(userRegistered);
        var response = userController.newUser(requisicao);
        assertEquals(responseExpected, response);
        assertTrue(response.contains(userRegistered.getName()));
    }

    @Test
    @DisplayName("Teste listar usuários")
    void testListUsers() {
        var user1 = UserTestUtil.getUserResponse(1, false);
        var user2 = UserTestUtil.getUserResponse(2, true);
        var listResponseService = List.of(user1, user2);
        when(userService.listUsers()).thenReturn(listResponseService);
        var response = userController.listUsers();
        assertEquals(2, response.size());
        assertTrue(response.getLast().admin());
        assertFalse(response.getFirst().admin());
    }

    @Test
    @DisplayName("Teste aplicar permissão de administrador ao usuario")
    void testUserAdminApply() {
        var userResponse = UserTestUtil.getUserResponse(1, true);
        var userRequest = UserTestUtil.getUserRoleDTo(userResponse.getId(), true);
        when(userService.alterRoleUser(userRequest.userId(), userRequest.hasRoleAdmin())).thenReturn(userResponse);
        var response = userController.updateUserRole(userRequest);
        assertTrue(response.hasRoleAdmin());
        assertEquals(userResponse.getId(), response.userId());
    }

}
