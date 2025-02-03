package com.company.books.backend.controllers;

import com.company.books.backend.model.Libro;
import com.company.books.backend.response.LibroResponseRest;
import com.company.books.backend.service.ILibroService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/libros/{id}")
    public ResponseEntity<LibroResponseRest> obtenerLibro(@PathVariable Long id) {
        return service.obtenerLibro(id);
    }

    @PostMapping("/libros")
    public ResponseEntity<LibroResponseRest> crearLibro(@RequestBody Libro libro) {
        return service.crearLibro(libro);
    }

    @PutMapping("/libros/{id}")
    public ResponseEntity<LibroResponseRest> actualizarLibro(@PathVariable Long id, @RequestBody Libro libro) {
        return service.actualizarLibro(id, libro);
    }

    @DeleteMapping("/libros/{id}")
    public ResponseEntity<LibroResponseRest> borrarLibro(@PathVariable Long id) {
        return service.borrarLibro(id);
    }

}
