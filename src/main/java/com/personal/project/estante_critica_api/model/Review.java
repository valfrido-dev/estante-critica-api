package com.personal.project.estante_critica_api.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "reviews")
public class Review {

    @Id
    private String id;
    private String userId;
    private String bookId;

    private String comments;
    private Integer numberRating;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;

}
