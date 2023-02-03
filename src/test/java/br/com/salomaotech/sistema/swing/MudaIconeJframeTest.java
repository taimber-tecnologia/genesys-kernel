package br.com.salomaotech.sistema.swing;

import javax.swing.JFrame;
import org.junit.Test;
import static org.junit.Assert.*;

public class MudaIconeJframeTest {

    @Test
    public void testAlterar() {

        /* testa o nome do icone como nulo */
        JFrame view = new JFrame();
        System.out.println("Testando classe MudaIconeJframe metodo: alterar etapa 01");
        assertEquals(false, new MudaIconeJframe().alterar(null, view));

        /* testa um icone que não existe */
        view = new JFrame();
        System.out.println("Testando classe MudaIconeJframe metodo: alterar etapa 02");
        assertEquals(false, new MudaIconeJframe().alterar("gatoxxx", view));

        /* testa um icone que existe */
        view = new JFrame();
        System.out.println("Testando classe MudaIconeJframe metodo: alterar etapa 03");
        assertEquals(true, new MudaIconeJframe().alterar("gato", view));

    }

}
