package com.company.books.backend.ejemplos.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AssertTruFalseTeoria {

    @Test
    public void test() {
        Assertions.assertTrue(true);
        Assertions.assertFalse(false);
    }


    @Test
    public void test2() {
        boolean expresion = 4 == 4;

        Assertions.assertTrue(expresion);
        Assertions.assertFalse(expresion);
    }

}
