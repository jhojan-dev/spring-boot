package com.company.books.backend.service;

import com.company.books.backend.model.Libro;
import com.company.books.backend.response.LibroResponseRest;
import org.springframework.http.ResponseEntity;

public interface ILibroService {

    ResponseEntity<LibroResponseRest> obtenerTodos();
    ResponseEntity<LibroResponseRest> obtenerLibro(Long id);
    ResponseEntity<LibroResponseRest> crearLibro(Libro libro);
    ResponseEntity<LibroResponseRest> actualizarLibro(Long id, Libro libro);
    ResponseEntity<LibroResponseRest> borrarLibro(Long id);

}
