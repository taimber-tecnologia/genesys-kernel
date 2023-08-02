package br.com.salomaotech.genesys.controller.novidades;

import org.junit.Test;
import static org.junit.Assert.*;

public class ExibeNovidadesTest {

    @Test
    public void testExibir() {

        boolean isErro = false;

        try {

            ExibeNovidades.exibir();

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe ExibeNovidades método: exibir");
        assertEquals(false, isErro);

    }

}
