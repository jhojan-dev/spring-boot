package com.company.books.backend.service;

import com.company.books.backend.response.CategoriaResponseRest;
import org.springframework.http.ResponseEntity;

public interface ICategoriaService {

    ResponseEntity<CategoriaResponseRest> buscarCategorias();
}
