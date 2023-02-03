package br.com.salomaotech.sistema.swing;

import java.awt.event.MouseListener;
import javax.swing.JTextField;
import org.junit.Test;
import static org.junit.Assert.*;

public class PopUpTest {

    @Test
    public void testAdicionarMenu() {

        JTextField jt = new JTextField();

        PopUp popUp = new PopUp();
        popUp.adicionarMenu(jt);
        boolean eventoPopUpMenuEncontrado = false;

        for (MouseListener classe : jt.getMouseListeners()) {

            if (classe.getClass().toString().contains("swing.PopUp")) {

                eventoPopUpMenuEncontrado = true;
                break;

            }

        }

        System.out.println("Testando classe PopUp metodo: adicionarMenu");
        assertEquals(true, eventoPopUpMenuEncontrado);

    }

}
