package br.com.salomaotech.sistema.swing;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class JTableRenderizadorCaixaAltaTest {

    @Test
    public void testRenderizar() {

        /* cria um modelo de tabela de exemplo */
        String[] columnNames = {"Coluna1", "Coluna2", "Coluna3"};
        Object[][] data = {{"celula1", "Celula2", "CeLuLa3"}, {"celula4", "Celula5", "CeLuLa6"}};

        DefaultTableModel model = new DefaultTableModel(data, columnNames);

        /* cria a tabela com o modelo de tabela criado */
        JTable jtable = new JTable(model);

        /* renderiza a tabela */
        JTableRenderizadorCaixaAlta renderizador = new JTableRenderizadorCaixaAlta();
        renderizador.renderizar(jtable);

        /* verifica se todas as células foram colocadas em caixa alta */
        int rowCount = model.getRowCount();
        int colCount = model.getColumnCount();

        System.out.println("Testando classe JTableRenderizadorCaixaAlta método: renderizar");

        /* itera as linhas */
        for (int row = 0; row < rowCount; row++) {

            /* itera as colunas */
            for (int col = 0; col < colCount; col++) {

                Object value = model.getValueAt(row, col);

                if (value instanceof String) {

                    assertEquals(((String) value).toUpperCase(), value);

                }

            }

        }

    }

}
