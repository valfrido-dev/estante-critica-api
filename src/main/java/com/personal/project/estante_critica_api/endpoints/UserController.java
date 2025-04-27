package com.personal.project.estante_critica_api.endpoints;

import com.personal.project.estante_critica_api.endpoints.dto.user.*;
import com.personal.project.estante_critica_api.model.User;
import com.personal.project.estante_critica_api.security.JwtUtilService;
import com.personal.project.estante_critica_api.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService service;
    private final AuthenticationManager authenticationManager;
    private final JwtUtilService jwtUtilService;

    @PostMapping("/user/register")
    @Transactional
    public String newUser(@RequestBody @Valid NewUserDTO newUser) {
        User user = service.registerNewUser(newUser);
        return String.format("Usuário incluído com sucesso! Bem vindo: %s!", user.getName());
    }

    @PostMapping("/user/login")
    public ResponseAutenticatorDTO loginUser(@RequestBody @Valid RequestAutenticatorDTO userAuthentication) {
        var usernamePassword =
                new UsernamePasswordAuthenticationToken(userAuthentication.username(), userAuthentication.password());
        var auth = authenticationManager.authenticate(usernamePassword);
        var token = jwtUtilService.generateToken((UserDetails) auth.getPrincipal());
        return new ResponseAutenticatorDTO(token);
    }

    @GetMapping("/list")
    public List<UserDTO> listUsers() {
        var listUsers = service.listUsers();
        return listUsers.stream().map(user -> new UserDTO(user.getId(),
                user.getUsername(), user.getName(), user.getEmail(),
                user.getAdmin(), user.getBooks(), user.getCreateDate(), user.getUpdateDate()))
                .toList();
    }

    @PostMapping("/user/admin/apply")
    public UserRoleDTO updateUserRole(@RequestBody @Valid UserRoleDTO userUpdate) {
        var userUpdated = service.alterRoleUser(userUpdate.userId(), userUpdate.hasRoleAdmin());
        return new UserRoleDTO(userUpdated.getId(), userUpdated.getAdmin());
    }

}
