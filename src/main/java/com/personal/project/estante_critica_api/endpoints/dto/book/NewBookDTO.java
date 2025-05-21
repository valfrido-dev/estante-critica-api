package com.personal.project.estante_critica_api.endpoints.dto.book;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.List;

public record NewBookDTO(
        @NotBlank
        String title,
        @DefaultValue(value = "")
        String subtitle,
        @NotNull
        List<String> authors,
        @NotBlank
        String category,
        @NotBlank
        String publisher,
        @NotBlank
        String publicationDate,

        @DefaultValue(value = "")
        String thumbnail,
        @DefaultValue(value = "")
        @Size(max = 500)
        String synopsis) {
}
