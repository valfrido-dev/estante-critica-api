package com.personal.project.estante_critica_api.service;

import com.personal.project.estante_critica_api.exceptions.BookAlreadyExistsException;
import com.personal.project.estante_critica_api.exceptions.LibrarySizeException;
import com.personal.project.estante_critica_api.exceptions.ResourceNotFoundException;
import com.personal.project.estante_critica_api.exceptions.UserNotFoundException;
import com.personal.project.estante_critica_api.model.Book;
import com.personal.project.estante_critica_api.model.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class LibraryUserService {

    private static final String MESSAGE_NOT_AUTENTICATED = "Login não realizado!";

    private final BookService bookService;
    private final UserService userService;

    public List<Book> listLibrary() {
        var userAutenticated = userService.getUserAutenticated()
                .orElseThrow(() -> new UserNotFoundException(MESSAGE_NOT_AUTENTICATED));
        return this.bookService.findByIds(userAutenticated.getBooks());
    }

    @Transactional
    public Book addBookInLibrary(String bookId) {
        var userAutenticated = userService.getUserAutenticated()
                .orElseThrow(() -> new UserNotFoundException(MESSAGE_NOT_AUTENTICATED));
        var book = bookService.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Livro não localizado!"));
        this.valid(userAutenticated, book);
        this.addBooksOnUser(userAutenticated, book);
        this.userService.saveUser(userAutenticated);
        return book;
    }

    @Transactional
    public void removeBookInLibrary(String bookId) {
        var userAutenticated = userService.getUserAutenticated()
                .orElseThrow(() -> new UserNotFoundException(MESSAGE_NOT_AUTENTICATED));
        this.removeBookOnUser(userAutenticated, bookId);
        this.userService.saveUser(userAutenticated);
    }

    public Boolean verifyBookInLibrary(String bookId) {
        var userAutenticated = userService.getUserAutenticated()
                .orElseThrow(() -> new UserNotFoundException(MESSAGE_NOT_AUTENTICATED));
        return this.hasBookInLibrary(userAutenticated, bookId);
    }

    private void valid(User user, Book book) {
        if (user.getBooks() != null && user.getBooks().size() == 10) {
            throw new LibrarySizeException("A lista de livros de interesse já está cheia!");
        }

        if (user.getBooks() != null && this.hasBookInLibrary(user, book.getId())) {
            throw new BookAlreadyExistsException("Livro já está na lista de interesse!");
        }
    }

    private boolean hasBookInLibrary(User user, String bookId) {
        return user.getBooks().stream().anyMatch(id -> this.isEqualsBookId(id, bookId));
    }

    private boolean isEqualsBookId(String idBookLibrary, String bookId) {
        return idBookLibrary.matches(bookId);
    }

    private void addBooksOnUser(User userAuth, Book book) {
        if (userAuth.getBooks() != null) {
            userAuth.getBooks().add(book.getId());
        } else {
            userAuth.setBooks(List.of(book.getId()));
        }
    }

    private void removeBookOnUser(User userAuth, String bookId) {
        if (userAuth.getBooks() != null) {
            userAuth.getBooks().remove(bookId);
        }
    }


}
