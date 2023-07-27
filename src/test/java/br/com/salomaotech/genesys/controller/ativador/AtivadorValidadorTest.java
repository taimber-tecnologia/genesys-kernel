package br.com.salomaotech.genesys.controller.ativador;

import br.com.salomaotech.genesys.view.JFativador;
import org.junit.Test;
import static org.junit.Assert.*;

public class AtivadorValidadorTest {

    private final JFativador view = new JFativador();
    private final AtivadorValidador validador = new AtivadorValidador(view);

    @Test
    public void testIsValido() {

        /* utilizando filtro: nenhum */
        view.jTchave.setText(null);
        System.out.println("Testando classe AtivadorValidador metodo: isValido etapa 01");
        assertEquals(false, validador.isValido());

        /* utilizando filtro: chave inválida */
        view.jTchave.setText("ABC");
        System.out.println("Testando classe AtivadorValidador metodo: isValido etapa 02");
        assertEquals(true, validador.isValido());

    }

}
