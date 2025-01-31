package com.company.books.backend.response;

import java.io.Serializable;

public class LibroResponseRest extends ResponseRest implements Serializable {

    private static final long serialVersionUID = 1L;

    private LibroResponse libroResponse;

    public LibroResponseRest() {
        this.libroResponse = new LibroResponse();
    }

    public LibroResponse getLibroResponse() {
        return libroResponse;
    }

    public void setLibroResponse(LibroResponse libroResponse) {
        this.libroResponse = libroResponse;
    }
}
