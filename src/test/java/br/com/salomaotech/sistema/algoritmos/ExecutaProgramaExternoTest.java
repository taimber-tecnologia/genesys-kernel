package br.com.salomaotech.sistema.algoritmos;

import org.junit.Test;
import static org.junit.Assert.*;

public class ExecutaProgramaExternoTest {

    @Test
    public void testExecutarModoDesktop() {

        System.out.println("Testando classe ExecutaProgramaExterno metodo: executarModoDesktop");
        assertEquals(false, ExecutaProgramaExterno.executarModoDesktop(null));

    }

    @Test
    public void testExecutarModoJar() {

        System.out.println("Testando classe ExecutaProgramaExterno metodo: executarModoJar");
        assertEquals(false, ExecutaProgramaExterno.executarModoJar(null, null));

    }

    @Test
    public void testAbreUrlNoBrowser() {

        System.out.println("Testando classe ExecutaProgramaExterno metodo: abreUrlNoBrowser");
        assertEquals(true, ExecutaProgramaExterno.abreUrlNoBrowser("https://www.taimber.com/"));

    }

}
