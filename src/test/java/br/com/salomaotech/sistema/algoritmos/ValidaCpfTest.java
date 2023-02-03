package br.com.salomaotech.sistema.algoritmos;

import org.junit.Test;
import static org.junit.Assert.*;

public class ValidaCpfTest {

    @Test
    public void testIsValido() {

        System.out.println("Testando classe ValidaCpf metodo: isValido etapa 01");
        assertEquals(true, ValidaCpf.isValido("825.800.590-16"));

        System.out.println("Testando classe ValidaCpf metodo: isValido etapa 02");
        assertEquals(true, ValidaCpf.isValido("82580059016"));

        System.out.println("Testando classe ValidaCpf metodo: isValido etapa 03");
        assertEquals(false, ValidaCpf.isValido("000.000.000-00"));

        System.out.println("Testando classe ValidaCpf metodo: isValido etapa 04");
        assertEquals(false, ValidaCpf.isValido(""));

    }

}
