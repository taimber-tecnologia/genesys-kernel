package br.com.salomaotech.sistema.algoritmos;

import org.junit.Test;
import static org.junit.Assert.*;

public class IsStringNumeroTest {

    @Test
    public void testIsNumero() {

        System.out.println("Testando classe IsStringNumero metodo: isNumeroValido etapa 01");
        assertEquals(true, IsStringNumeroValido.isNumeroValido("0"));

        System.out.println("Testando classe IsStringNumero metodo: isNumeroValido etapa 02");
        assertEquals(true, IsStringNumeroValido.isNumeroValido("0.0"));

        System.out.println("Testando classe IsStringNumero metodo: isNumeroValido etapa 03");
        assertEquals(true, IsStringNumeroValido.isNumeroValido("199"));

        System.out.println("Testando classe IsStringNumero metodo: isNumeroValido etapa 04");
        assertEquals(true, IsStringNumeroValido.isNumeroValido("199.99"));

        System.out.println("Testando classe IsStringNumero metodo: isNumeroValido etapa 05");
        assertEquals(true, IsStringNumeroValido.isNumeroValido("199,99"));

        System.out.println("Testando classe IsStringNumero metodo: isNumeroValido etapa 06");
        assertEquals(false, IsStringNumeroValido.isNumeroValido("199.999.99"));

        System.out.println("Testando classe IsStringNumero metodo: isNumeroValido etapa 07");
        assertEquals(false, IsStringNumeroValido.isNumeroValido("199.999,99"));

        System.out.println("Testando classe IsStringNumero metodo: isNumeroValido etapa 08");
        assertEquals(false, IsStringNumeroValido.isNumeroValido("199,999,99"));

        System.out.println("Testando classe IsStringNumero metodo: isNumeroValido etapa 09");
        assertEquals(true, IsStringNumeroValido.isNumeroValido("-0"));

        System.out.println("Testando classe IsStringNumero metodo: isNumeroValido etapa 10");
        assertEquals(true, IsStringNumeroValido.isNumeroValido("-0.0"));

        System.out.println("Testando classe IsStringNumero metodo: isNumeroValido etapa 11");
        assertEquals(true, IsStringNumeroValido.isNumeroValido("-199"));

        System.out.println("Testando classe IsStringNumero metodo: isNumeroValido etapa 12");
        assertEquals(true, IsStringNumeroValido.isNumeroValido("-199.99"));

        System.out.println("Testando classe IsStringNumero metodo: isNumeroValido etapa 13");
        assertEquals(true, IsStringNumeroValido.isNumeroValido("-199,99"));

        System.out.println("Testando classe IsStringNumero metodo: isNumeroValido etapa 14");
        assertEquals(false, IsStringNumeroValido.isNumeroValido("-199.999.99"));

        System.out.println("Testando classe IsStringNumero metodo: isNumeroValido etapa 15");
        assertEquals(false, IsStringNumeroValido.isNumeroValido("-199.999,99"));

        System.out.println("Testando classe IsStringNumero metodo: isNumeroValido etapa 16");
        assertEquals(false, IsStringNumeroValido.isNumeroValido("-199,999,99"));

        System.out.println("Testando classe IsStringNumero metodo: isNumeroValido etapa 17");
        assertEquals(false, IsStringNumeroValido.isNumeroValido("abc"));

        System.out.println("Testando classe IsStringNumero metodo: isNumeroValido etapa 18");
        assertEquals(false, IsStringNumeroValido.isNumeroValido("-abc"));

        System.out.println("Testando classe IsStringNumero metodo: isNumeroValido etapa 19");
        assertEquals(false, IsStringNumeroValido.isNumeroValido("123abc"));

        System.out.println("Testando classe IsStringNumero metodo: isNumeroValido etapa 20");
        assertEquals(false, IsStringNumeroValido.isNumeroValido("-123abc"));

    }

}
