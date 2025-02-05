package com.company.books.backend.ejemplos.junit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AssertNotEqualsTeoria {

    @Test
    public void test() {
        Assertions.assertNotEquals(2, 1);
        Assertions.assertNotEquals(2, 2);
    }
}
