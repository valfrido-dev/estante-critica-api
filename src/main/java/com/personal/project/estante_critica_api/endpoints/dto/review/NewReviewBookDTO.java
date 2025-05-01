package com.personal.project.estante_critica_api.endpoints.dto.review;

import jakarta.validation.constraints.*;
import org.springframework.boot.context.properties.bind.DefaultValue;


public record NewReviewBookDTO(
        @DefaultValue(value = "")
        String id,
        @NotBlank
        String bookId,
        @NotNull
        @Min(value = 1, message = "O valor mínimo para a avaliação é 1")
        @Max(value = 5, message = "O valor máximo para a avaliação é 5")
        Integer numberRating,
        @DefaultValue(value = "")
        @Size(max = 500)
        String comments) {
}
