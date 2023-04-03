package br.com.salomaotech.sistema.swing;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class JTableRenderizadorCaixaAlta extends DefaultTableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable tabela, Object valor, boolean isSelected, boolean hasFocus, int linha, int coluna) {

        Component componente = super.getTableCellRendererComponent(tabela, valor, isSelected, hasFocus, linha, coluna);

        if (componente instanceof JLabel) {

            JLabel label = (JLabel) componente;
            label.setText(label.getText().toUpperCase());

        }

        return componente;

    }

}
