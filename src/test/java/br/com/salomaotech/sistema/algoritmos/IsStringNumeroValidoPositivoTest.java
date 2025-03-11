package br.com.salomaotech.sistema.algoritmos;

import org.junit.Test;
import static org.junit.Assert.*;

public class IsStringNumeroValidoPositivoTest {

    @Test
    public void testIsNumeroPositivo() {

        System.out.println("Testando classe IsStringNumeroValidoPositivo método isNumeroPositivo etapa 01");
        assertEquals(true, IsStringNumeroValidoPositivo.isNumeroPositivo("1"));

        System.out.println("Testando classe IsStringNumeroValidoPositivo método isNumeroPositivo etapa 02");
        assertEquals(false, IsStringNumeroValidoPositivo.isNumeroPositivo("-1"));

        System.out.println("Testando classe IsStringNumeroValidoPositivo método isNumeroPositivo etapa 03");
        assertEquals(true, IsStringNumeroValidoPositivo.isNumeroPositivo("0"));

        System.out.println("Testando classe IsStringNumeroValidoPositivo método isNumeroPositivo etapa 04");
        assertEquals(true, IsStringNumeroValidoPositivo.isNumeroPositivo("12345"));

        System.out.println("Testando classe IsStringNumeroValidoPositivo método isNumeroPositivo etapa 05");
        assertEquals(false, IsStringNumeroValidoPositivo.isNumeroPositivo("-12345"));

        System.out.println("Testando classe IsStringNumeroValidoPositivo método isNumeroPositivo etapa 06");
        assertEquals(true, IsStringNumeroValidoPositivo.isNumeroPositivo("123.45"));

        System.out.println("Testando classe IsStringNumeroValidoPositivo método isNumeroPositivo etapa 07");
        assertEquals(false, IsStringNumeroValidoPositivo.isNumeroPositivo("-123.45"));

        System.out.println("Testando classe IsStringNumeroValidoPositivo método isNumeroPositivo etapa 08");
        assertEquals(false, IsStringNumeroValidoPositivo.isNumeroPositivo("abc"));

        System.out.println("Testando classe IsStringNumeroValidoPositivo método isNumeroPositivo etapa 09");
        assertEquals(false, IsStringNumeroValidoPositivo.isNumeroPositivo(" "));

        System.out.println("Testando classe IsStringNumeroValidoPositivo método isNumeroPositivo etapa 10");
        assertEquals(true, IsStringNumeroValidoPositivo.isNumeroPositivo("0.01"));

        System.out.println("Testando classe IsStringNumeroValidoPositivo método isNumeroPositivo etapa 11");
        assertEquals(false, IsStringNumeroValidoPositivo.isNumeroPositivo("-0.01"));

    }

}
