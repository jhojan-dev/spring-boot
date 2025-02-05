package com.company.books.backend.ejemplos.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AssertArrayEquals {

    @Test
    public void pruebaArregloIguales() {
        String[] arreglo = new String[] { "A", "B" };
        String[] arreglo2 = new String[] { "A", "B" };
        String[] arreglo3 = new String[] { "A", "B", "C" };

        Assertions.assertArrayEquals(arreglo, arreglo2);
        Assertions.assertArrayEquals(arreglo, arreglo3);
        Assertions.assertArrayEquals(arreglo2, arreglo3);
    }

}
