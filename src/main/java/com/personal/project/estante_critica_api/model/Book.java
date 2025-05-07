package com.personal.project.estante_critica_api.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Document(collection = "books")
public class Book {

    @Id
    private String id;

    private String title;
    private String subtitle;
    private List<String> authors;
    private String category;
    private String publisher;
    private String publicationDate;
    private String synopsis;
    private Double numberAverageRating;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private String thumbnail;

}
