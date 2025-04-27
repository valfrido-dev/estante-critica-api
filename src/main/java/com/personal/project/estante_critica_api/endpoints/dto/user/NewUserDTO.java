package com.personal.project.estante_critica_api.endpoints.dto.user;


import jakarta.validation.constraints.NotBlank;

public record NewUserDTO(
        @NotBlank(message = "O nome de usuário não foi informado!")
        String username,
        @NotBlank(message = "Nome não foi informado")
        String name,
        @NotBlank(message = "O e-mail não foi informado!")
        String email,
        @NotBlank(message = "Senha não informada!")
        String password) {
}
