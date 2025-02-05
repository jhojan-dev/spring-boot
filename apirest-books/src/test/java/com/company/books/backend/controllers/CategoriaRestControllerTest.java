package com.company.books.backend.controllers;

import com.company.books.backend.model.Categoria;
import com.company.books.backend.response.CategoriaResponseRest;
import com.company.books.backend.service.ICategoriaService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class CategoriaRestControllerTest {

    @InjectMocks
    private CategoriaRestController categoriaRestController;

    @Mock
    private ICategoriaService service;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void crearCategoriaTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Categoria categoria = new Categoria();
        categoria.setNombre("Test");
        categoria.setDescripcion("A test description");

        Mockito.when(
                service.crear( ArgumentMatchers.any(Categoria.class) )
        ).thenReturn(new ResponseEntity<>(HttpStatus.CREATED));

        ResponseEntity<CategoriaResponseRest> response = categoriaRestController.crear(categoria);

        Assertions.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

}
