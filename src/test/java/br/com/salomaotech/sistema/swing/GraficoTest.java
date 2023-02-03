package br.com.salomaotech.sistema.swing;

import java.math.BigDecimal;
import static java.util.Objects.isNull;
import org.junit.Test;
import static org.junit.Assert.*;

public class GraficoTest {

    private final Grafico grafico = new Grafico();

    @Test
    public void testSetTituloTopo() {

        boolean isErro = false;

        try {

            grafico.setTituloTopo("Teste");

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe Grafico metodo: setTituloTopo");
        assertEquals(false, isErro);

    }

    @Test
    public void testSetTituloRodape() {

        boolean isErro = false;

        try {

            grafico.setTituloRodape("Teste");

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe Grafico metodo: setTituloRodape");
        assertEquals(false, isErro);

    }

    @Test
    public void testSetLegendaEsquerda() {

        boolean isErro = false;

        try {

            grafico.setLegendaEsquerda("Teste");

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe Grafico metodo: setLegendaEsquerda");
        assertEquals(false, isErro);

    }

    @Test
    public void testAddCategoria() {

        boolean isErro = false;

        try {

            grafico.addCategoria(new BigDecimal(100), "Teste");

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe Grafico metodo: addCategoria");
        assertEquals(false, isErro);

    }

    @Test
    public void testConstruir() {

        System.out.println("Testando classe Grafico metodo: construir");
        assertEquals(true, !isNull(grafico.construir()));

    }

}
