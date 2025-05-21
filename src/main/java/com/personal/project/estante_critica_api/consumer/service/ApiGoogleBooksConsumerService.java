package com.personal.project.estante_critica_api.consumer.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.personal.project.estante_critica_api.consumer.dto.ResponseGoogleBooksAPIDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;


@RequiredArgsConstructor
@Slf4j
@Service
public class ApiGoogleBooksConsumerService {
    private static final String URL_SEARCH_GOOGLE_BOOKS = "https://www.googleapis.com/books/v1/volumes";

    private final ObjectMapper objectMapper = new ObjectMapper();
    private final RestTemplate restTemplate;

    public String getApiResponse(String titleSearch, String publisherSearch) {
        var querySearch = String.format("?q=search+intitle:%s+inpublisher:%s", titleSearch, publisherSearch);
        var uriSearch = URL_SEARCH_GOOGLE_BOOKS + querySearch;

        try {
            // Make a GET request to the API
            var response = restTemplate.getForObject(uriSearch, String.class);
            ResponseGoogleBooksAPIDTO dto = objectMapper.readValue(response, ResponseGoogleBooksAPIDTO.class);
            return this.getImageThumbnailUri(dto);
        } catch (RestClientException | JsonProcessingException e) {
            log.error("Erro ao consultar Api Google Books para buscar thumbnail URL!");
            return "";
        }
    }

    private String getImageThumbnailUri(ResponseGoogleBooksAPIDTO responseApi) {
        if (responseApi.totalItems() != null && responseApi.totalItems() > 0) {
            var itemDTO = responseApi.items().getFirst();
            if (itemDTO != null && itemDTO.volumeInfo() != null
                    && itemDTO.volumeInfo().imageLinks().thumbnail() != null) {
                return itemDTO.volumeInfo().imageLinks().thumbnail();
            }
        }
        return "";
    }

}
