package com.company.books.backend.ejemplos.junit;

import org.junit.jupiter.api.*;

public class CalculadoraTest {

    Calculadora calculadora;

    @BeforeAll
    public static void primero() throws Exception {
        System.out.println("Primero");
    }

    @AfterAll
    public static void ultimo() throws Exception {
        System.out.println("Ultimo");
    }

    @BeforeEach
    public void instaciaObjeto() {
        calculadora = new Calculadora();
        System.out.println("@BeforeEach");
    }

    @AfterEach
    public void despuesTest() {
        System.out.println("@AfterEach");
    }

    @Test
    @DisplayName("prueba que ocupa assertEquals")
    @Disabled("Esta prueba no se ejecutará")
    public void calculadoraAssertEqualTest() {

        Assertions.assertEquals(2, calculadora.sum(1, 1));
        Assertions.assertEquals(3, calculadora.restar(4, 1));
        Assertions.assertEquals(9, calculadora.multiplicar(3, 3));
        Assertions.assertEquals(2, calculadora.dividir(4, 2));

        System.out.println("calculadoraAssertEqualTest");
    }

    @Test
    public void calculadoraTrueFalse() {
        Assertions.assertTrue(calculadora.sum(1, 1) == 2);
        Assertions.assertTrue(calculadora.restar(4, 1) == 3);
        Assertions.assertFalse((calculadora.multiplicar(3, 3) == 8));
        Assertions.assertFalse((calculadora.dividir(4, 2) == 1));

        System.out.println("calculadoraTrueFalse");
    }

}
