package com.company.books.backend.service;

import com.company.books.backend.model.Categoria;
import com.company.books.backend.model.dao.ICategoriaDao;
import com.company.books.backend.response.CategoriaResponseRest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class CategoriaServiceImplTest {

    @InjectMocks
    private CategoriaServiceImpl categoriaService;

    @Mock
    private ICategoriaDao categoriaDao;

    List<Categoria> categorias;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
        cargarCategorias();
    }

    @Test
    public void buscarCategoriaTest() {
        Mockito.when(categoriaDao.findAll()).thenReturn(categorias);
        ResponseEntity<CategoriaResponseRest> response = categoriaService.buscarCategorias();
        Assertions.assertEquals(4, response.getBody().getCategoriaResponse().getCategorias().size());
        Mockito.verify(categoriaDao, Mockito.times(1)).findAll();
    }

    public void cargarCategorias() {
        categorias = List.of(
                new Categoria(1L, "Abarrotes", "Distintos Abarrotes"),
                new Categoria(2L, "Lacteos", "Variedad de bebidas"),
                new Categoria(3L, "Bebidas", "Bebidas sin azúcar"),
                new Categoria(4L, "Carnes Blancas", "Distintas carnes")
        );

    }

}