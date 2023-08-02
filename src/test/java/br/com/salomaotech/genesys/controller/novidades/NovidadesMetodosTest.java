package br.com.salomaotech.genesys.controller.novidades;

import br.com.salomaotech.sistema.modelos.ApiModelo;
import org.junit.Test;
import static org.junit.Assert.*;

public class NovidadesMetodosTest {

    @Test
    public void testExecutar() {

        boolean isErro = false;

        try {

            new NovidadesMetodos().executar(new ApiModelo());

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando a classe NovidadesMetodos método: executar");
        assertEquals(false, isErro);

    }

}
