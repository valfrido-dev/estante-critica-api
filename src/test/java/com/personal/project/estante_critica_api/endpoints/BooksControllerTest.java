package com.personal.project.estante_critica_api.endpoints;


import com.personal.project.estante_critica_api.exceptions.ResourceNotFoundException;
import com.personal.project.estante_critica_api.model.Book;
import com.personal.project.estante_critica_api.service.BookService;
import com.personal.project.estante_critica_api.util.BooksTestUtil;
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
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class BooksControllerTest {

    @InjectMocks
    private BooksController booksController;
    @Mock
    private BookService bookService;

    @Test
    @DisplayName("Teste listar livros")
    void testListBooks() {
        var book = BooksTestUtil.getBookResponse(1);
        var book2 = BooksTestUtil.getBookResponse(2);
        var listBooksResponse = List.of(book, book2);
        when(bookService.listBooks()).thenReturn(listBooksResponse);
        var result = booksController.listBooks();
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Teste buscar livros por categoria")
    void testListByCategory() {
        var book = BooksTestUtil.getBookResponse(1);
        var book2 = BooksTestUtil.getBookResponse(2);
        var categoryExpected = "teste";
        var listBooksResponse = List.of(book, book2);
        when(bookService.findByParams(
                eq(""), eq(""), eq(categoryExpected), eq(""))).thenReturn(listBooksResponse);
        var result = booksController.findBooks("", "", categoryExpected, "");
        assertEquals(2, result.size());
        assertEquals(categoryExpected, result.getFirst().getCategory());
        assertEquals(categoryExpected, result.getLast().getCategory());
    }

    @Test
    @DisplayName("Teste buscar livros por categoria - nenhum livro localizado")
    void testListByCategoryError() {
        var categoryExpected = "terror";
        List<Book> listBooksResponse = List.of();
        when(bookService.findByParams(
                eq(""), eq(""), eq(categoryExpected), eq(""))).thenReturn(listBooksResponse);
        Exception exception = assertThrows(ResourceNotFoundException.class,
                () -> booksController.findBooks("", "", categoryExpected, ""));
        assertEquals("Nenhum livro localizado para os parametros informados!", exception.getMessage());
    }

    @Test
    @DisplayName("Teste detalhar livro por identificador único")
    void testGetBook() {
        var bookExpected = BooksTestUtil.getBookResponse(1);
        var bookIdRequest = bookExpected.getId();
        when(bookService.findById(eq(bookIdRequest))).thenReturn(Optional.of(bookExpected));
        var result = booksController.getBook(bookIdRequest);
        assertEquals(bookIdRequest, result.getId());
    }

    @Test
    @DisplayName("Teste detalhar livro por identificador único - não localizado")
    void testGetBookError() {
        var bookIdRequest = BooksTestUtil.generatedBookId();
        when(bookService.findById(eq(bookIdRequest))).thenReturn(Optional.empty());
        Exception exception = assertThrows(ResourceNotFoundException.class,
                () -> booksController.getBook(bookIdRequest));
        assertEquals("Livro não localizado!", exception.getMessage());
    }

    @Test
    @DisplayName("Teste incluir novo livro na base de dados")
    void testBookRegister() {
        var bookExpected = BooksTestUtil.getBookResponse(1);
        var bookRegister = BooksTestUtil.getBookRegister(1);
        when(bookService.insertBook(bookRegister)).thenReturn(bookExpected);
        var result = booksController.registerBook(bookRegister);
        assertEquals("Livro Livro teste 1 incluído com sucesso!", result);
    }

}
