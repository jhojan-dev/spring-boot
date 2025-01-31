package com.company.books.backend.service;

import com.company.books.backend.response.LibroResponseRest;
import org.springframework.http.ResponseEntity;

public interface ILibroService {

    ResponseEntity<LibroResponseRest> obtenerTodos();

}
