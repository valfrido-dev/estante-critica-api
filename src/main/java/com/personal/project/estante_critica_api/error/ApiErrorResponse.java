package com.personal.project.estante_critica_api.error;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;
import java.util.List;

public record ApiErrorResponse(
        @JsonFormat(pattern="dd-MM-yyyy HH:mm:ss")
        LocalDateTime timestamp,
        Integer code,
        String status,
        List<String> errors
) {
}
