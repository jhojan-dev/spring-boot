package com.company.books.backend.controllers;

import com.company.books.backend.response.CategoriaResponseRest;
import com.company.books.backend.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class CategoriaRestController {
    @Autowired
    private ICategoriaService categoriaService;

    @GetMapping("/categorias")
    public ResponseEntity<CategoriaResponseRest> consultarCategoria() {
        return categoriaService.buscarCategorias();
    }
}
