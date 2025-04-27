package com.personal.project.estante_critica_api.endpoints.dto.user;

import java.time.LocalDateTime;
import java.util.List;


public record UserDTO(
        String id,
        String username,
        String name,
        String email,
        Boolean admin,
        List<String>books,
        LocalDateTime createDate,
        LocalDateTime updateDate) {
}
