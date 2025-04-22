package com.personal.project.estante_critica_api.endpoints;

import com.personal.project.estante_critica_api.endpoints.dto.UserDTO;
import com.personal.project.estante_critica_api.model.User;
import com.personal.project.estante_critica_api.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService service;

    @PostMapping("/user/register")
    @Transactional
    public String newUser(@RequestBody @Validated UserDTO newUser) {
        User user = service.registerNewUser(newUser);
        return String.format("Usuário incluído com sucesso! UserId: %s", user.getId());
    }

}
