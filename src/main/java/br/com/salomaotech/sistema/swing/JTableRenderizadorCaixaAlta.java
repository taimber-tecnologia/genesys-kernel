package br.com.salomaotech.sistema.swing;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class JTableRenderizadorCaixaAlta {

    public void renderizar(JTable table) {

        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int rowCount = model.getRowCount();
        int colCount = model.getColumnCount();

        for (int row = 0; row < rowCount; row++) {

            for (int col = 0; col < colCount; col++) {

                Object value = model.getValueAt(row, col);

                if (value instanceof String) {

                    String upperValue = ((String) value).toUpperCase();
                    model.setValueAt(upperValue, row, col);

                }

            }

        }

    }

}
