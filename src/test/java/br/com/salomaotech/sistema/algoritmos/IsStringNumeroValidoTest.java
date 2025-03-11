package br.com.salomaotech.sistema.algoritmos;

import org.junit.Test;
import static org.junit.Assert.*;

public class IsStringNumeroValidoTest {

    @Test
    public void testIsNumeroValido() {

        System.out.println("Testando classe IsStringNumeroValido método isNumeroValido etapa 01");
        assertEquals(true, IsStringNumeroValido.isNumeroValido("1"));

        System.out.println("Testando classe IsStringNumeroValido método isNumeroValido etapa 02");
        assertEquals(false, IsStringNumeroValido.isNumeroValido("abc"));

        System.out.println("Testando classe IsStringNumeroValido método isNumeroValido etapa 03");
        assertEquals(true, IsStringNumeroValido.isNumeroValido("12345"));

        System.out.println("Testando classe IsStringNumeroValido método isNumeroValido etapa 04");
        assertEquals(true, IsStringNumeroValido.isNumeroValido("123.45"));

        System.out.println("Testando classe IsStringNumeroValido método isNumeroValido etapa 05");
        assertEquals(true, IsStringNumeroValido.isNumeroValido("123,45"));

        System.out.println("Testando classe IsStringNumeroValido método isNumeroValido etapa 06");
        assertEquals(false, IsStringNumeroValido.isNumeroValido(" "));

        System.out.println("Testando classe IsStringNumeroValido método isNumeroValido etapa 07");
        assertEquals(true, IsStringNumeroValido.isNumeroValido("0"));

        System.out.println("Testando classe IsStringNumeroValido método isNumeroValido etapa 08");
        assertEquals(true, IsStringNumeroValido.isNumeroValido("-123"));

        System.out.println("Testando classe IsStringNumeroValido método isNumeroValido etapa 09");
        assertEquals(true, IsStringNumeroValido.isNumeroValido("-123.45"));

        System.out.println("Testando classe IsStringNumeroValido método isNumeroValido etapa 10");
        assertEquals(true, IsStringNumeroValido.isNumeroValido("123.00"));

        System.out.println("Testando classe IsStringNumeroValido método isNumeroValido etapa 11");
        assertEquals(false, IsStringNumeroValido.isNumeroValido("$123"));

        System.out.println("Testando classe IsStringNumeroValido método isNumeroValido etapa 12");
        assertEquals(false, IsStringNumeroValido.isNumeroValido("123€"));

        System.out.println("Testando classe IsStringNumeroValido método isNumeroValido etapa 13");
        assertEquals(false, IsStringNumeroValido.isNumeroValido("123#45"));

        System.out.println("Testando classe IsStringNumeroValido método isNumeroValido etapa 14");
        assertEquals(false, IsStringNumeroValido.isNumeroValido("123.45.67"));

        System.out.println("Testando classe IsStringNumeroValido método isNumeroValido etapa 15");
        assertEquals(false, IsStringNumeroValido.isNumeroValido("12@3"));

        System.out.println("Testando classe IsStringNumeroValido método isNumeroValido etapa 16");
        assertEquals(false, IsStringNumeroValido.isNumeroValido("123-45"));

        System.out.println("Testando classe IsStringNumeroValido método isNumeroValido etapa 17");
        assertEquals(false, IsStringNumeroValido.isNumeroValido("(123)"));

        System.out.println("Testando classe IsStringNumeroValido método isNumeroValido etapa 18");
        assertEquals(false, IsStringNumeroValido.isNumeroValido("12+34"));

        System.out.println("Testando classe IsStringNumeroValido método isNumeroValido etapa 19");
        assertEquals(false, IsStringNumeroValido.isNumeroValido("=123"));

        System.out.println("Testando classe IsStringNumeroValido método isNumeroValido etapa 20");
        assertEquals(false, IsStringNumeroValido.isNumeroValido("12^3"));

    }

}
