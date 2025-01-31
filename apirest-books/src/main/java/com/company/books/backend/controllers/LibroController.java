package com.company.books.backend.controllers;

import com.company.books.backend.response.LibroResponseRest;
import com.company.books.backend.service.ILibroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class LibroController {

    private final ILibroService service;

    public LibroController(ILibroService service) {
        this.service = service;
    }

    @GetMapping("/libros")
    public ResponseEntity<LibroResponseRest> obtenerTodos() {
        return service.obtenerTodos();
    }

}
