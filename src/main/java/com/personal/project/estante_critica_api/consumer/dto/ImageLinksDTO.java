package com.personal.project.estante_critica_api.consumer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ImageLinksDTO(String thumbnail) {
}
