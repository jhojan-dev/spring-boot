package com.company.books.backend.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseRest {
    private List<Map<String, String>> metadata = new ArrayList<Map<String, String>>();

    public List<Map<String, String>> getMetadata() {
        return this.metadata;
    }

    public void setMetadata(String tipo, String codigo, String dato) {
        Map<String, String> mapa = Map.of("tipo", tipo, "codigo", codigo, "dato", dato);
        metadata.add(mapa);
    }

}
