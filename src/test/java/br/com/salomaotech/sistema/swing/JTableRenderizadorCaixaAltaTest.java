package br.com.salomaotech.sistema.swing;

import java.awt.Component;
import org.junit.Test;
import static org.junit.Assert.*;

import javax.swing.JLabel;
import javax.swing.JTable;

public class JTableRenderizadorCaixaAltaTest {

    private final JTableRenderizadorCaixaAlta jTableRenderizadorCaixaAlta = new JTableRenderizadorCaixaAlta();

    @Test
    public void testGetTableCellRendererComponent() {

        JLabel label = new JLabel();
        label.setText("Olá, Mundo!");
        Component componente = jTableRenderizadorCaixaAlta.getTableCellRendererComponent(new JTable(), "Olá, Mundo!", false, false, 0, 0);

        System.out.println("Testando classe JTableRenderizadorCaixaAlta método: getTableCellRendererComponent");
        assertEquals(true, ((JLabel) componente).getText().equals("OLÁ, MUNDO!"));

    }

}
