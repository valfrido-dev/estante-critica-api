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
@RequestMapping("library")
@RequiredArgsConstructor
@Slf4j
public class LibraryUserController {

    private final LibraryUserService service;

    @GetMapping("/list")
    public List<Book> listLibrary() {
        return service.listLibrary();
    }
    @PostMapping("/user/book/add")
    public String addBookLibraryPersonal(@RequestBody @Valid BookLibraryDTO bookSelected) {
        var book = service.addBookInLibrary(bookSelected.bookId());
        return String.format("Livro %s adicionado a lista de interesse!", book.getTitle());
    }

    @PostMapping("/user/book/remove")
    public String removeBookLibraryPersonal(@RequestBody @Valid BookLibraryDTO bookSelected) {
        service.removeBookInLibrary(bookSelected.bookId());
        return "Livro %s removido da lista de interesse!";
    }

}
