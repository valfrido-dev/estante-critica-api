package com.personal.project.estante_critica_api.endpoints.dto.review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.boot.context.properties.bind.DefaultValue;

public record ReviewBookDTO(
        String id,
        @NotBlank
        String bookId,
        @NotNull
        Integer numberRating,
        @DefaultValue(value = "")
        String comments,
        String userOwner,
        String userOwnerId
) {
}
