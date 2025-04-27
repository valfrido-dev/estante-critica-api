package com.personal.project.estante_critica_api.endpoints.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UserRoleDTO(
        @NotBlank
        String userId,
        @NotNull
        Boolean hasRoleAdmin) {
}
