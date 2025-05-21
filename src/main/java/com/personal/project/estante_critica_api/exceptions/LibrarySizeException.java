package com.personal.project.estante_critica_api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class LibrarySizeException extends RuntimeException  {
    public LibrarySizeException(String message) {
        super(message);
    }
}
