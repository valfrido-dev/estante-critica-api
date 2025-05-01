package com.personal.project.estante_critica_api.util;

import com.personal.project.estante_critica_api.endpoints.dto.review.NewReviewBookDTO;
import com.personal.project.estante_critica_api.endpoints.dto.review.ReviewBookDTO;
import com.personal.project.estante_critica_api.model.Review;

import java.time.LocalDateTime;
import java.util.UUID;

public class ReviewTestUtil {

    private ReviewTestUtil() {

    }

    public static ReviewBookDTO getResponseMock(String bookId, Integer complement) {
        return new ReviewBookDTO(generatedReviewId(),
                bookId, 3, "Comments " + complement,
                "User Review", UserTestUtil.generatedUserId());
    }

    public static NewReviewBookDTO getResponseNewReviewMock(String id, String bookId, Integer complement) {
        return new NewReviewBookDTO(id, bookId, 3, "Comments " + complement);
    }

    public static Review getReviewMock(String bookId, String userId, Integer complement) {
        var reviewDataBase = new Review();
        reviewDataBase.setId(generatedReviewId());
        reviewDataBase.setBookId(bookId);
        reviewDataBase.setComments("Comments " + complement);
        reviewDataBase.setNumberRating(3);
        reviewDataBase.setUserId(userId);
        reviewDataBase.setUpdateDate(LocalDateTime.now());
        reviewDataBase.setCreateDate(LocalDateTime.now());
        return reviewDataBase;
    }


    public static String generatedReviewId() {
        return UUID.randomUUID().toString();
    }

}
