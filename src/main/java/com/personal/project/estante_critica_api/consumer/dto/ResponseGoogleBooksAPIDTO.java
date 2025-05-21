package com.personal.project.estante_critica_api.consumer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResponseGoogleBooksAPIDTO(Integer totalItems, List<ItemDTO> items) {
}
