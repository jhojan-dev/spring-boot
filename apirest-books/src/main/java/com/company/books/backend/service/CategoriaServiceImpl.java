package com.company.books.backend.service;

import com.company.books.backend.model.Categoria;
import com.company.books.backend.model.dao.ICategoriaDao;
import com.company.books.backend.response.CategoriaResponseRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
}
