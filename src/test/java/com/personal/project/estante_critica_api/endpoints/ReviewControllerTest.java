package com.personal.project.estante_critica_api.endpoints;


import com.personal.project.estante_critica_api.endpoints.dto.review.ReviewBookDTO;
import com.personal.project.estante_critica_api.service.BookService;
import com.personal.project.estante_critica_api.service.ReviewService;
import com.personal.project.estante_critica_api.util.BooksTestUtil;
import com.personal.project.estante_critica_api.util.ReviewTestUtil;
import com.personal.project.estante_critica_api.util.UserTestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ReviewControllerTest {

    @InjectMocks
    private ReviewController reviewController;
    @Mock
    private ReviewService reviewService;

    @Test
    @DisplayName("Teste listar livros")
    void testListBooks() {
        var book = BooksTestUtil.getBookResponse(1);
        var reviewExpected = ReviewTestUtil.getResponseMock(book.getId(), 1);
        var reviewExpected2= ReviewTestUtil.getResponseMock(book.getId(), 2);
        var listResponseExpected = List.of(reviewExpected, reviewExpected2);
        when(reviewService.listReview(book.getId())).thenReturn(listResponseExpected);
        var result = reviewController.listReviewByBook(book.getId());
        assertEquals(2, result.size());
        assertEquals("Comments 1", result.getFirst().comments());
        assertEquals("Comments 2", result.getLast().comments());
    }

    @Test
    @DisplayName("Teste avaliar livro comentando")
    void testNewReview() {
        var book = BooksTestUtil.getBookResponse(1);
        var reviewExpected = ReviewTestUtil
                .getReviewMock(book.getId(), UserTestUtil.generatedUserId(), 1);
        var newReviewRequest = ReviewTestUtil
                .getResponseNewReviewMock(reviewExpected.getId(), book.getId(), 1);
        when(reviewService.reviewBook(book.getId(), 3, "Comments 1")).thenReturn(reviewExpected);
        var result = reviewController.createNewReviewBook(newReviewRequest);
        assertEquals(reviewExpected.getId(), result.id());
        assertEquals(reviewExpected.getComments(), result.comments());
        assertEquals(reviewExpected.getNumberRating(), result.numberRating());
    }

}
