package br.com.salomaotech.sistema.algoritmos;

import org.junit.Test;
import static org.junit.Assert.*;

public class ValidaStringIsEmptyTest {

    @Test
    public void testIsEmpty() {

        String nome = "Salomão";
        System.out.println("Testando classe ValidaStringIsEmpty metodo: isEmpty etapa 01");
        assertEquals(false, ValidaStringIsEmpty.isEmpty(nome));

        nome = "";
        System.out.println("Testando classe ValidaStringIsEmpty metodo: isEmpty etapa 02");
        assertEquals(true, ValidaStringIsEmpty.isEmpty(nome));

        nome = null;
        System.out.println("Testando classe ValidaStringIsEmpty metodo: isEmpty etapa 03");
        assertEquals(true, ValidaStringIsEmpty.isEmpty(nome));

    }

}
