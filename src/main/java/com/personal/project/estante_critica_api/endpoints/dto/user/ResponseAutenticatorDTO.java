package com.personal.project.estante_critica_api.endpoints.dto.user;

public record ResponseAutenticatorDTO(String token, String username, String name, Boolean isAdmin) {
}
