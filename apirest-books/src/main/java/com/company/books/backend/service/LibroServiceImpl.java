package com.company.books.backend.service;

import com.company.books.backend.model.Libro;
import com.company.books.backend.model.dao.ILibroDao;
import com.company.books.backend.response.LibroResponseRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LibroServiceImpl implements ILibroService {

    private static final Logger log = LoggerFactory.getLogger(CategoriaServiceImpl.class);
    private final ILibroDao libroDao;

    public LibroServiceImpl(ILibroDao libroDao) {
        this.libroDao = libroDao;
    }

    @Override
    public ResponseEntity<LibroResponseRest> obtenerTodos() {
        LibroResponseRest response = new LibroResponseRest();
        try {
            List<Libro> libros = libroDao.findAll();
            response.setMetadata("Respuesta Ok", "00", "Respuesta Exitosa");
            response.getLibroResponse().setLibros(libros);
        } catch (Exception e) {
            response.setMetadata("Respuesta Nok", "-1", "Respuesta incorrecta");
            log.error("Error al obtener libros", e.getMessage());
            e.getStackTrace();
            return ResponseEntity.internalServerError().body(response);
        }
        return ResponseEntity.ok(response);
    }
}
