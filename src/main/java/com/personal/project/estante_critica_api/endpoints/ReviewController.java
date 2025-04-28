package com.personal.project.estante_critica_api.endpoints;

import com.personal.project.estante_critica_api.endpoints.dto.review.NewReviewBookDTO;
import com.personal.project.estante_critica_api.endpoints.dto.review.ReviewBookDTO;
import com.personal.project.estante_critica_api.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/book/review")
@RequiredArgsConstructor
@Slf4j
public class ReviewController {

    private final ReviewService service;

    @GetMapping("/list")
    public List<ReviewBookDTO> listReviewByBook(@RequestParam String bookId) {
        return service.listReview(bookId);
    }

    @PostMapping("/new")
    public NewReviewBookDTO createNewReviewBook(@RequestBody @Valid NewReviewBookDTO review) {
        var newReview = service.reviewBook(review.bookId(), review.numberRating(), review.comments());
        return new NewReviewBookDTO(
                newReview.getId(),
                newReview.getBookId(),
                newReview.getNumberRating(),
                newReview.getComments());
    }


}
