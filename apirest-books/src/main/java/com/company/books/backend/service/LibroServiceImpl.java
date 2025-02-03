package com.company.books.backend.service;

import com.company.books.backend.model.Libro;
import com.company.books.backend.model.dao.ILibroDao;
import com.company.books.backend.response.LibroResponseRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class LibroServiceImpl implements ILibroService {

    private static final Logger log = LoggerFactory.getLogger(CategoriaServiceImpl.class);
    private final ILibroDao libroDao;

    public LibroServiceImpl(ILibroDao libroDao) {
        this.libroDao = libroDao;
    }

    @Override
    @Transactional(readOnly = true)
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

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<LibroResponseRest> obtenerLibro(Long id) {
        LibroResponseRest response = new LibroResponseRest();
        try {
            Optional<Libro> libro = libroDao.findById(id);
            if (libro.isPresent()) {
                response.setMetadata("Respuesta Ok", "00", "Se encontro el libro");
                response.getLibroResponse().setLibros(List.of(libro.get()));
            } else {
                response.setMetadata("Respuesta Nok", "-1", "No se encontraron registros");
            }
        } catch (Exception e) {
            log.error("Error al obtener libros", e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta Nok", "-1", "Error al obtener libros");
            return ResponseEntity.internalServerError().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @Override
    @Transactional
    public ResponseEntity<LibroResponseRest> crearLibro(Libro libro) {
        LibroResponseRest response = new LibroResponseRest();
        try {
            Libro libroDB = libroDao.save(libro);
            if(libroDB != null) {
                response.setMetadata("Respuesta Ok", "00", "Respuesta Exitosa");
                response.getLibroResponse().setLibros(List.of(libroDB));
            } else {
                response.setMetadata("Respuesta Nok", "-1", "No se pudo crear el libro");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception e) {
            log.error("Error al crear libro", e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta Nok", "00", "Error al crear libro");
            return ResponseEntity.internalServerError().body(response);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    @Transactional
    public ResponseEntity<LibroResponseRest> actualizarLibro(Long id, Libro libro) {
        LibroResponseRest response = new LibroResponseRest();
        try {
            Optional<Libro> libroDB = libroDao.findById(id);
            if (libroDB.isEmpty()) {
                response.setMetadata("Respuesta Nok", "-1", "No se encontro el libro");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            libroDB.get().setNombre(libro.getNombre());
            libroDB.get().setDescripcion(libro.getDescripcion());
            libroDB.get().setCategoria(libro.getCategoria());
            Libro libroActualizado = libroDao.save(libroDB.get());

            if(libroActualizado == null) {
                response.setMetadata("Respuesta Nok", "-1", "No se pudo actualizar el libro");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }

            response.setMetadata("Respuesta Ok", "00", "Respuesta Exitosa");
            response.getLibroResponse().setLibros(List.of(libroDB.get()));
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            log.error("Error al crear libro", e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta Nok", "00", "Error al crear libro");
            return ResponseEntity.internalServerError().body(response);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<LibroResponseRest> borrarLibro(Long id) {
        LibroResponseRest response = new LibroResponseRest();
        try {
            if(!libroDao.existsById(id)) {
                response.setMetadata("Respuesta Nok", "-1", "No se encontro el libro");
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
            }
            libroDao.deleteById(id);
            response.setMetadata("Respuesta Ok", "00", "Respuesta Exitosa");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            log.error("Error al borrar libro", e.getMessage());
            e.getStackTrace();
            response.setMetadata("Respuesta Nok", "00", "Error al borrar libro");
            return ResponseEntity.internalServerError().body(response);
        }
    }
}
