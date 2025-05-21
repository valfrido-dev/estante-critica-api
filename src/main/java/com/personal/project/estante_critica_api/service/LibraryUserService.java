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

    private final BookService bookService;
    private final UserService userService;

    public List<Book> listLibrary() {
        var userAutenticated = userService.getUserAutenticated()
                .orElseThrow(() -> new UserNotFoundException("Usuário não localizado, login não realizado!"));
        return this.bookService.findByIds(userAutenticated.getBooks());
    }

    @Transactional
    public Book addBookInLibrary(String bookId) {
        var userAutenticated = userService.getUserAutenticated()
                .orElseThrow(() -> new UserNotFoundException("Usuário não localizado, login não realizado!"));
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
                .orElseThrow(() -> new UserNotFoundException("Usuário não localizado, login não realizado!"));
        this.removeBookOnUser(userAutenticated, bookId);
        this.userService.saveUser(userAutenticated);
    }

    private void valid(User user, Book book) {
        if (user.getBooks() != null && user.getBooks().size() == 10) {
            throw new LibrarySizeException("A lista de livros de interesse já está cheia!");
        }

        if (user.getBooks() != null && user.getBooks().stream().anyMatch(id -> this.hasBookInLibrary(id, book))) {
            throw new BookAlreadyExistsException("Livro já está na lista de interesse!");
        }

    }

    private boolean hasBookInLibrary(String bookId, Book book) {
        return bookId.matches(book.getId());
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
