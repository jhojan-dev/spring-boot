package com.company.books.backend.service;

import com.company.books.backend.model.Categoria;
import com.company.books.backend.model.dao.ICategoriaDao;
import com.company.books.backend.response.CategoriaResponseRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements ICategoriaService {

    private static final Logger log = LoggerFactory.getLogger(CategoriaServiceImpl.class);

    @Autowired
    private ICategoriaDao categoriaDao;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoriaResponseRest> buscarCategorias() {
        log.info("Buscando categorias");
        CategoriaResponseRest response = new CategoriaResponseRest();
        try {
            List<Categoria> categorias = (List<Categoria>) categoriaDao.findAll();
            response.getCategoriaResponse().setCategorias(categorias);
            response.setMetadata("Respuesta Ok", "00", "Respuesta Exitosa");
        } catch (Exception e) {
            response.setMetadata("Respuesta Nok", "-1", "Respuesta incorrecta");
            log.error("Error al buscar categorias", e.getMessage());
            e.getStackTrace();
            return ResponseEntity.internalServerError().body(response);
        }
        return ResponseEntity.ok(response);
    }

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity<CategoriaResponseRest> buscarCategoriaPorId(Long categoriaId) {
        log.info("Buscando categoria por id: {}", categoriaId);
        CategoriaResponseRest response = new CategoriaResponseRest();
        try {
            Optional<Categoria> categoriaOptional = categoriaDao.findById(categoriaId);
            if (categoriaOptional.isPresent()) {
                Categoria categoria = categoriaOptional.get();
                response.getCategoriaResponse().setCategorias(List.of(categoria));
                response.setMetadata("Respuesta Ok", "00", "Respuesta Exitosa");
            } else {
                log.info("Categoria no encontrada");
                response.setMetadata("Respuesta Nok", "-1", "Categoria no encontrada");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception e) {
            log.error("Error al buscar categoria", e);
            response.setMetadata("Respuesta Nok", "-1", "Error al consultar la categoria");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.ok(response);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoriaResponseRest> crear(Categoria categoria) {
        log.info("Creando categoria: {}", categoria);
        CategoriaResponseRest response = new CategoriaResponseRest();
        try {
            Categoria categoriaDB = categoriaDao.save(categoria);
            if (categoriaDB != null) {
                response.getCategoriaResponse().setCategorias(List.of(categoriaDB));
            } else {
                log.info("Error al crear categoria");
                response.setMetadata("Respuesta Nok", "-1", "Error al crear categoria");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception e) {
            log.error("Error al buscar categoria", e);
            response.setMetadata("Respuesta Nok", "-1", "Error al crear la categoria");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        response.setMetadata("Respuesta Ok", "00", "Respuesta Exitosa");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoriaResponseRest> actualizar(Categoria categoria, Long categoriaId) {
        log.info("Actualizando categoria: {}", categoria);
        CategoriaResponseRest response = new CategoriaResponseRest();
        List<Categoria> list = new ArrayList<>();
        try {
            Optional<Categoria> categoriaDbOptional = categoriaDao.findById(categoriaId);
            if (categoriaDbOptional.isPresent()) {
                Categoria categoriaDB = categoriaDbOptional.get();
                categoriaDB.setNombre(categoria.getNombre());
                categoriaDB.setDescripcion(categoria.getDescripcion());

                categoriaDB = categoriaDao.save(categoriaDB);
                if (categoriaDB != null) {
                    list.add(categoriaDB);
                    response.getCategoriaResponse().setCategorias(list);
                } else {
                    log.info("Error al actualizar categoria");
                    response.setMetadata("Respuesta Nok", "-1", "Error al actualizar categoria");
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
                }
            } else {
                log.info("Categoria no encontrada");
                response.setMetadata("Respuesta Nok", "-1", "Categoria no encontrada");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception e) {
            log.error("Error al actualizar categoria", e);
            response.setMetadata("Respuesta Nok", "-1", "Error al actualizar categoria");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        response.setMetadata("Respuesta Ok", "00", "Respuesta Exitosa");
        return ResponseEntity.ok(response);
    }

    @Override
    @Transactional
    public ResponseEntity<CategoriaResponseRest> eliminar(Long id) {
        log.info("Eliminando categoria: {}", id);
        CategoriaResponseRest response = new CategoriaResponseRest();

        try {
            Optional<Categoria> categoriaOptional = categoriaDao.findById(id);
            if (categoriaOptional.isPresent()) {
                categoriaDao.deleteById(id);
                response.setMetadata("Respuesta Ok", "00", "Categoria Eliminada");
            } else {
                log.info("Categoria no encontrada");
                response.setMetadata("Respuesta Nok", "-1", "Categoria no encontrada");
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
            }
        } catch (Exception e) {
            log.error("Error al eliminar categoria", e);
            e.getStackTrace();
            response.setMetadata("Respuesta Nok", "-1", "Error al eliminar categoria");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
        return ResponseEntity.ok(response);
    }
}
