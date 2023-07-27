package br.com.salomaotech.genesys.controller.ativador;

import br.com.salomaotech.genesys.view.JFativador;
import org.junit.Test;
import static org.junit.Assert.*;

public class AtivadorEventosTest {

    private final JFativador view = new JFativador();
    private final AtivadorEventos ativadorEventos = new AtivadorEventos(view);

    @Test
    public void testAddEventos() {

        /* eventos */
        ativadorEventos.addEventos();

        /* testa se os eventos foram adicionados */
        System.out.println("Testando classe AtivadorEventos metodo: addEventos");
        assertEquals(true, view.jBativar.getActionListeners().length == 1);
        assertEquals(true, view.jLabrirSuporte.getMouseListeners().length == 1);

    }

}
