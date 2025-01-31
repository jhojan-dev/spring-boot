package com.company.books.backend.response;

import com.company.books.backend.model.Libro;

import java.io.Serializable;
import java.util.List;

public class LibroResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<Libro> libros;

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}
