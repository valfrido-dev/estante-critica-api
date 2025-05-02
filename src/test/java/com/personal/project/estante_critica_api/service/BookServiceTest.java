package com.personal.project.estante_critica_api.service;

import com.personal.project.estante_critica_api.model.Book;
import com.personal.project.estante_critica_api.repository.BookRepository;
import com.personal.project.estante_critica_api.repository.ReviewRepository;
import com.personal.project.estante_critica_api.util.BooksTestUtil;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository repository;
    @Mock
    private MongoTemplate mongoTemplate;


    @Test
    @DisplayName("Teste listar livros")
    void testListBooks() {
        var book = BooksTestUtil.getBookResponse(1);
        var book2 = BooksTestUtil.getBookResponse(2);
        var listResponse = List.of(book, book2);
        var sort = Sort.by(Sort.Direction.DESC, "numberAverageRating");
        when(repository.findAll(sort)).thenReturn(listResponse);
        var result = bookService.listBooks();
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Teste buscar livro por identificador único")
    void testFindById() {
        var book = BooksTestUtil.getBookResponse(1);
        when(repository.findById(book.getId())).thenReturn(Optional.of(book));
        var result = bookService.findById(book.getId());
        assertTrue(result.isPresent());
        assertEquals(book.getId(), result.get().getId());
    }

    @Test
    @DisplayName("Teste buscar livro por parametros - por categoria")
    void testFindByParams() {
        var book = BooksTestUtil.getBookResponse(1);
        List<Book> listResponse = List.of(book);
        when(mongoTemplate.find(any(), eq(Book.class))).thenReturn(listResponse);
        var result = bookService.findByParams("", "", "teste", "");
        assertEquals(1, result.size());
        assertEquals(book.getTitle(), result.getFirst().getTitle());
    }

    @Test
    @DisplayName("Teste buscar livro por parametros - todos parametros")
    void testFindByParamsAll() {
        var book = BooksTestUtil.getBookResponse(1);
        List<Book> listResponse = List.of(book);
        when(mongoTemplate.find(any(), eq(Book.class))).thenReturn(listResponse);
        var result = bookService.findByParams(
                "Livro teste 1", "Autor 1", "teste", "Autor");
        assertEquals(1, result.size());
        assertEquals(book.getTitle(), result.getFirst().getTitle());
    }

    @Test
    @DisplayName("Teste savar livro - alterar livro update")
    void testSaveBook() {
        var book = BooksTestUtil.getBookResponse(1);
        bookService.saveBook(book);
        verify(repository, times(1)).save(book);
    }


}
