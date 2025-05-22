package com.personal.project.estante_critica_api.endpoints;

import com.personal.project.estante_critica_api.endpoints.dto.book.BookLibraryDTO;
import com.personal.project.estante_critica_api.model.Book;
import com.personal.project.estante_critica_api.service.LibraryUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
@RequiredArgsConstructor
@Slf4j
public class LibraryUserController {

    private final LibraryUserService service;

    @GetMapping("/list")
    public List<Book> listLibrary() {
        return service.listLibrary();
    }

    @PostMapping("/user/hasBook")
    public BookLibraryDTO verifyBookInLibrary(@RequestBody @Valid BookLibraryDTO bookSelected) {
        var hasBookInLibrary = service.verifyBookInLibrary(bookSelected.bookId());
        return new BookLibraryDTO(bookSelected.bookId(), bookSelected.bookTitle(), hasBookInLibrary);
    }

    @PostMapping("/user/book/add")
    public BookLibraryDTO addBookLibraryPersonal(@RequestBody @Valid BookLibraryDTO bookSelected) {
        var book = service.addBookInLibrary(bookSelected.bookId());
        return new BookLibraryDTO(book.getId(), book.getTitle(), Boolean.TRUE);
    }

    @PostMapping("/user/book/remove")
    public BookLibraryDTO removeBookLibraryPersonal(@RequestBody @Valid BookLibraryDTO bookSelected) {
        service.removeBookInLibrary(bookSelected.bookId());
        return new BookLibraryDTO(bookSelected.bookId(), bookSelected.bookTitle(), Boolean.FALSE);
    }

}
