package com.personal.project.estante_critica_api.endpoints;

import com.personal.project.estante_critica_api.exceptions.ResourceNotFoundException;
import com.personal.project.estante_critica_api.model.Book;
import com.personal.project.estante_critica_api.service.BookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
@Slf4j
public class BooksController {

    private final BookService service;

    @GetMapping("/list")
    public List<Book> listBooks() {
        return service.listBooks();
    }

    @GetMapping("/find")
    public List<Book> findBooks(@RequestParam(name = "title", required = false) String title,
                              @RequestParam(name = "author", required = false) String author,
                              @RequestParam(name = "category", required = false) String category,
                              @RequestParam(name = "publisher", required = false) String publisher) {
        List<Book> listSearchResponse = service.findByParams(title, author, category, publisher);

        if ( listSearchResponse.isEmpty() ) {
            log.warn("Nenhum livro localizado para os parametros informados!");
            throw new ResourceNotFoundException("Nenhum livro localizado para os parametros informados!");
        }

        return listSearchResponse;
    }

    @GetMapping("/book")
    public Book getBook(@RequestParam(name = "bookId") String bookId) {
        Optional<Book> bookPovided = service.findById(bookId);

        if (bookPovided.isEmpty()) {
            log.warn("Livro não localizado! Identificador: {}", bookId);
            throw new ResourceNotFoundException("Livro não localizado!");
        }

        return bookPovided.get();
    }

}
