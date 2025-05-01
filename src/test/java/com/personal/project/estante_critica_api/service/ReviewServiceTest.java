package com.personal.project.estante_critica_api.service;


import com.personal.project.estante_critica_api.exceptions.ResourceNotFoundException;
import com.personal.project.estante_critica_api.exceptions.ReviewAlreadyExistsException;
import com.personal.project.estante_critica_api.exceptions.UserNotFoundException;
import com.personal.project.estante_critica_api.repository.ReviewRepository;
import com.personal.project.estante_critica_api.util.BooksTestUtil;
import com.personal.project.estante_critica_api.util.ReviewTestUtil;
import com.personal.project.estante_critica_api.util.UserTestUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class ReviewServiceTest {

    @InjectMocks
    private ReviewService reviewService;

    @Mock
    private ReviewRepository repository;
    @Mock
    private UserService userService;
    @Mock
    private BookService bookService;


    @Test
    @DisplayName("Teste avaliar livro - erro usuario já avaliou o livro")
    void testNewReviewBookError() {
        var userAutenticated = UserTestUtil.getUserResponse(1, false);
        var book = BooksTestUtil.getBookResponse(1);
        var reviewExpected = ReviewTestUtil.getReviewMock(book.getId(), userAutenticated.getId(), 1);
        when(userService.getUserAutenticated()).thenReturn(Optional.of(userAutenticated));
        when(bookService.findById(book.getId())).thenReturn(Optional.of(book));
        when(repository.existsByBookIdAndUserId(book.getId(), userAutenticated.getId())).thenReturn(true);
        when(repository.findByBookId(book.getId())).thenReturn(List.of());
        when(repository.save(reviewExpected)).thenReturn(reviewExpected);
        Exception exception = assertThrows(ReviewAlreadyExistsException.class,
                () -> reviewService.reviewBook(book.getId(), 3, "Comments 1"));
        assertEquals("Livro Livro teste 1 já avaliado pelo usuário!", exception.getMessage());
    }

    @Test
    @DisplayName("Teste avaliar livro - usuario não localizado - sem autenticacao")
    void testNewReviewBookError2() {
        var book = BooksTestUtil.getBookResponse(1);
        when(userService.getUserAutenticated()).thenReturn(Optional.empty());
        Exception exception = assertThrows(UserNotFoundException.class,
                () -> reviewService.reviewBook(book.getId(), 3, "Comments 1"));
        assertEquals("Usuário não localizado, login não realizado!", exception.getMessage());
    }

    @Test
    @DisplayName("Teste avaliar livro - livro não localizado")
    void testNewReviewBookError3() {
        var userAutenticated = UserTestUtil.getUserResponse(1, false);
        var book = BooksTestUtil.getBookResponse(1);
        when(userService.getUserAutenticated()).thenReturn(Optional.of(userAutenticated));
        when(bookService.findById(book.getId())).thenReturn(Optional.empty());
        Exception exception = assertThrows(ResourceNotFoundException.class,
                () -> reviewService.reviewBook(book.getId(), 3, "Comments 1"));
        assertEquals("Livro não localizado!", exception.getMessage());
    }

    @Test
    @DisplayName("Teste lista avaliacoes por livro")
    void testListReviewBook() {
        var user = UserTestUtil.getUserResponse(1, false);
        var user2 = UserTestUtil.getUserResponse(2, false);
        var book = BooksTestUtil.getBookResponse(1);
        var reviewExpected = ReviewTestUtil.getReviewMock(book.getId(), user.getId(), 1);
        var reviewExpected2 = ReviewTestUtil.getReviewMock(book.getId(), user2.getId(), 2);
        var listReview = List.of(reviewExpected, reviewExpected2);
        when(repository.findByBookId(book.getId())).thenReturn(listReview);
        when(userService.getUsrById(user.getId())).thenReturn(Optional.of(user));
        when(userService.getUsrById(user2.getId())).thenReturn(Optional.of(user2));
        var result = reviewService.listReview(book.getId());
        assertEquals(2, result.size());
        assertEquals(reviewExpected.getId(), result.getFirst().id());
    }

    @Test
    @DisplayName("Teste lista avaliacoes por livro - Erro usuário não localizado")
    void testListReviewBookError1() {
        var book = BooksTestUtil.getBookResponse(1);
        var reviewExpected = ReviewTestUtil.getReviewMock(book.getId(), UserTestUtil.generatedUserId(), 1);
        var listReview = List.of(reviewExpected);
        when(repository.findByBookId(book.getId())).thenReturn(listReview);
        // gera novo id de usuario para retornar erro
        when(userService.getUsrById(UserTestUtil.generatedUserId())).thenReturn(Optional.empty());
        Exception exception = Assertions.assertThrows(UserNotFoundException.class,
                () -> reviewService.listReview(book.getId()));
        assertEquals("Usuário não localizado!", exception.getMessage());
    }

}
