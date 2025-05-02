package com.personal.project.estante_critica_api.service;

import com.personal.project.estante_critica_api.endpoints.dto.user.NewUserDTO;
import com.personal.project.estante_critica_api.model.User;
import com.personal.project.estante_critica_api.repository.UserRepository;
import com.personal.project.estante_critica_api.service.validators.user.UserValidatorImpl;
import com.personal.project.estante_critica_api.util.UserTestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository repository;
    @Mock
    private List<UserValidatorImpl<NewUserDTO>> validatorsDTO;
    @Mock
    private List<UserValidatorImpl<User>> validators;

    @Test
    @DisplayName("Teste cadastrar novo usuario")
    void testRegisterNewUser() {
        var user = UserTestUtil.getUserResponse(1, Boolean.FALSE);
        var newUserRequisicao = UserTestUtil.getNewUserRequisicao(1);
        when(repository.save(any())).thenReturn(user);
        var result = userService.registerNewUser(newUserRequisicao);
        assertTrue(result.isEnabled());
        assertEquals(user.getName(), result.getName());
    }

    @Test
    @DisplayName("Teste listar usuarios")
    void testListUser() {
        var user1 = UserTestUtil.getUserResponse(1, Boolean.FALSE);
        var user2 = UserTestUtil.getUserResponse(2, Boolean.FALSE);
        var listResponse = List.of(user1, user2);
        when(repository.findAll()).thenReturn(listResponse);
        var result = userService.listUsers();
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Teste buscar usuario por identificador")
    void testGetUsrById() {
        var user = UserTestUtil.getUserResponse(1, Boolean.FALSE);
        when(repository.findById(user.getId())).thenReturn(Optional.of(user));
        var result = userService.getUsrById(user.getId());
        assertTrue(result.isPresent());
        assertEquals(user.getId(), result.get().getId());
    }

    @Test
    @DisplayName("Teste alterar permissão do usuario")
    void testAlterRoleUser() {
        var user = UserTestUtil.getUserResponse(1, Boolean.FALSE);
        when(repository.findById(user.getId())).thenReturn(Optional.of(user));
        when(repository.save(user)).thenReturn(user);
        var result = userService.alterRoleUser(user.getId(), Boolean.TRUE);
        assertTrue(result.getAdmin());
    }

}
