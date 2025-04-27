package com.personal.project.estante_critica_api.endpoints.dto.user;

import jakarta.validation.constraints.NotBlank;


public record RequestAutenticatorDTO(
        @NotBlank
        String username,
        @NotBlank
        String password) {
}
