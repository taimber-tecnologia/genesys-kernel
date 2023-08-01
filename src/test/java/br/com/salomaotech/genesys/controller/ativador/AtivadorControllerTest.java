package br.com.salomaotech.genesys.controller.ativador;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class AtivadorControllerTest {

    private final String nomeSistema = "kernel-genesys-2.0";
    private final AtivadorController ativadorController = new AtivadorController();

    @Test
    public void testConstruir() {

        boolean isErro = false;

        try {

            ativadorController.construir(nomeSistema);

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe AtivadorController metodo: construir");
        assertEquals(false, isErro);

    }

}
