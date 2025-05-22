package com.personal.project.estante_critica_api.endpoints.dto.book;

import jakarta.validation.constraints.NotBlank;
import org.springframework.boot.context.properties.bind.DefaultValue;

public record BookLibraryDTO(
        @NotBlank String bookId,
        @DefaultValue(value = "")
        String bookTitle,
        @DefaultValue(value = "false")
        Boolean hasBook) {
}
