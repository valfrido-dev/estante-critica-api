package com.personal.project.estante_critica_api.util;

import com.personal.project.estante_critica_api.endpoints.dto.book.NewBookDTO;
import com.personal.project.estante_critica_api.model.Book;

import java.util.List;
import java.util.UUID;

public class BooksTestUtil {

    private BooksTestUtil() {

    }

    public static Book getBookResponse(Integer identificadorTest) {
        var book = new Book();
        book.setId(generatedBookId());
        book.setTitle(String.format("Livro teste %d", identificadorTest));
        book.setPublisher("Autor");
        book.setAuthors(List.of("Autor 1", "Autor 2"));
        book.setCategory("teste");
        book.setPublicationDate("04/2025");
        book.setSynopsis("Resumo teste");
        book.setNumberAverageRating(2.0);
        return book;
    }

    public static NewBookDTO getBookRegister(Integer identificadorTest) {
        var title = String.format("Livro teste %d", identificadorTest);
        return new NewBookDTO(title,
                "", List.of("Autor"),
                "teste",
                "Autor",
                "14/2025",
                "resumo");
    }

    public static String generatedBookId() {
        return UUID.randomUUID().toString();
    }


}
