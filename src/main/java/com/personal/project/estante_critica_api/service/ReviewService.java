package com.personal.project.estante_critica_api.service;

import com.personal.project.estante_critica_api.endpoints.dto.review.ReviewBookDTO;
import com.personal.project.estante_critica_api.exceptions.ResourceNotFoundException;
import com.personal.project.estante_critica_api.exceptions.ReviewAlreadyExistsException;
import com.personal.project.estante_critica_api.exceptions.UserNotFoundException;
import com.personal.project.estante_critica_api.model.Review;
import com.personal.project.estante_critica_api.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReviewService {

    private final ReviewRepository repository;
    private final UserService userService;
    private final BookService bookService;


    @Transactional
    public Review reviewBook(String bookId, Integer numberRating, String comments) {
        var userReview = userService.getUserAutenticated()
                .orElseThrow(() -> new UserNotFoundException("Usuário não localizado, login não realizado!"));
        var bookReview = bookService.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Livro não localizado!"));
        this.hasUserReviewForBook(bookReview.getId(), bookReview.getTitle(), userReview.getId());
        var review = this.createReview(bookReview.getId(), userReview.getId(), numberRating, comments);
        review.setCreateDate(LocalDateTime.now());
        review.setUpdateDate(LocalDateTime.now());
        var averageRating = this.calcAverageReting(bookReview.getId(), numberRating);
        bookReview.setNumberAverageRating(averageRating);
        bookService.saveBook(bookReview);
        return repository.save(review);
    }

    public List<ReviewBookDTO> listReview(String bookId) {
        List<Review> listReview = this.listReviewByBook(bookId);
        return listReview.stream()
                .map(this::getReviewResponse)
                .toList();
    }

    private ReviewBookDTO getReviewResponse(Review review) {
        var user = userService.getUsrById(
                review.getUserId()).orElseThrow(() -> new UserNotFoundException("Usuário não localizado!"));
        return new ReviewBookDTO(review.getId(), review.getBookId(),
                review.getNumberRating(), review.getComments(),
                user.getName(), review.getUserId());
    }

    private List<Review> listReviewByBook(String bookId) {
        return repository.findByBookId(bookId);
    }

    private Double calcAverageReting(String bookId, Integer numberRating) {
        List<Review> listReview = this.listReviewByBook(bookId);
        var sumRating = listReview.stream()
                .reduce(0, (sub, review) -> sub + review.getNumberRating(), Integer::sum);
        var countRating = listReview.size() + 1;
        return (double) (sumRating + numberRating) / countRating;
    }

    private Review createReview(String bookId, String userId,
                                Integer numberReating, String comments) {
        var review = new Review();
        review.setBookId(bookId);
        review.setUserId(userId);
        review.setNumberRating(numberReating);
        review.setComments(comments);
        return review;
    }

    private void hasUserReviewForBook(String bookId, String bookTitle, String userId) {
        var exists = repository.existsByBookIdAndUserId(bookId, userId);
        if (Boolean.TRUE.equals(exists)) {
            throw new ReviewAlreadyExistsException(String.format("Livro %s já avaliado pelo usuário!", bookTitle));
        }
    }


}
