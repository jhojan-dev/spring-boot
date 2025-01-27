package com.company.books.backend.response;

public class CategoriaResponseRest extends ResponseRest {
    private CategoriaResponse  categoriaResponse = new CategoriaResponse();

    public CategoriaResponse getCategoria() {
        return categoriaResponse;
    }

    public void setCategoria(CategoriaResponse categoriaResponse) {
        this.categoriaResponse = categoriaResponse;
    }
}
