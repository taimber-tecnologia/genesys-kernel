package br.com.salomaotech.genesys;

import org.junit.Test;
import static org.junit.Assert.*;

public class AppTest {

    @Test
    public void testMain() throws Exception {

        boolean isErro = false;

        try {

            App.main(null);

        } catch (Exception ex) {

            isErro = true;

        }

        System.out.println("Testando classe App metodo: main");
        assertEquals(false, isErro);

    }

}
