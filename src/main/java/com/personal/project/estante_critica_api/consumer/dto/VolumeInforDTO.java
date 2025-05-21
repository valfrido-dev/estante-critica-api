package com.personal.project.estante_critica_api.consumer.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VolumeInforDTO(
        String title,
        String subtitle,
        List<String> authors,
        String publisher,
        String publishedDate,
        ImageLinksDTO imageLinks) {
}
