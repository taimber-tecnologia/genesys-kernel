package br.com.salomaotech.sistema.swing;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import org.junit.Test;
import static org.junit.Assert.*;

public class MudaCorLinhaJtableTest {

    @Test
    public void testMudar() {

        Object[][] dados = {
            {"A", "000000"},
            {"B", "111111"},
            {"C", "111111"}
        };

        String[] colunas = {"Nome", "Telefone"};

        JTable tabela = new JTable(dados, colunas);

        List<Color> cores = new ArrayList();
        cores.add(Color.decode("#ffe4b2"));

        MudaCorLinhaJtable.mudar(tabela, cores);

        TableCellRenderer renderer = tabela.getCellRenderer(0, 0);
        Component c = tabela.prepareRenderer(renderer, 0, 0);

        System.out.println("Testando classe MudaCorLinhaJtable metodo: mudar");
        assertEquals(true, c.getBackground().toString().equals("java.awt.Color[r=255,g=228,b=178]"));

    }

}
