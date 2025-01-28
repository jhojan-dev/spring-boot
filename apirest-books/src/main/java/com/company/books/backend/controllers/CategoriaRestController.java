package com.company.books.backend.controllers;

import com.company.books.backend.model.Categoria;
import com.company.books.backend.response.CategoriaResponseRest;
import com.company.books.backend.service.ICategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
public class CategoriaRestController {
    @Autowired
    private ICategoriaService categoriaService;

    @GetMapping("/categorias")
    public ResponseEntity<CategoriaResponseRest> consultarCategoria() {
        return categoriaService.buscarCategorias();
    }

    @GetMapping("/categorias/{id}")
    public ResponseEntity<CategoriaResponseRest> consultarCategoriaPorId(@PathVariable Long id) {
        return categoriaService.buscarCategoriaPorId(id);
    }

    @PostMapping("/categorias")
    public ResponseEntity<CategoriaResponseRest> crear(@RequestBody Categoria request) {
        return categoriaService.crear(request);
    }



}
