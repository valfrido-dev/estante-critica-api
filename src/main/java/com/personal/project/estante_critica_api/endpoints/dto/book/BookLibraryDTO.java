package com.personal.project.estante_critica_api.endpoints.dto.book;

import jakarta.validation.constraints.NotBlank;

public record BookLibraryDTO(@NotBlank String bookId) {
}
