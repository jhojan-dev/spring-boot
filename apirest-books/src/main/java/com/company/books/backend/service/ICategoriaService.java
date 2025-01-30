package com.company.books.backend.service;

import com.company.books.backend.model.Categoria;
import com.company.books.backend.response.CategoriaResponseRest;
import org.springframework.http.ResponseEntity;

public interface ICategoriaService {

    ResponseEntity<CategoriaResponseRest> buscarCategorias();
    ResponseEntity<CategoriaResponseRest> buscarCategoriaPorId(Long categoriaId);
    ResponseEntity<CategoriaResponseRest> crear(Categoria categoria);
    ResponseEntity<CategoriaResponseRest> actualizar(Categoria categoria, Long categoriaId);
    ResponseEntity<CategoriaResponseRest> eliminar(Long id);
}
