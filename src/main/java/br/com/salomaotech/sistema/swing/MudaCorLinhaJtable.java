package br.com.salomaotech.sistema.swing;

import java.awt.Color;
import java.awt.Component;
import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MudaCorLinhaJtable {

    /**
     * Mudar a cor das linhas de um JTable
     *
     * @param jtTabela JTable
     * @param cores List de cores
     */
    public static void mudar(JTable jtTabela, List<Color> cores) {

        jtTabela.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {

            @Override
            public Component getTableCellRendererComponent(JTable tabelaView, Object valor, boolean isSelecionado, boolean temFoco, int linha, int coluna) {

                super.getTableCellRendererComponent(tabelaView, valor, isSelecionado, temFoco, linha, coluna);

                int posicao = 0;

                for (Color cor : cores) {

                    if (linha == posicao) {

                        setBackground(cor);

                    }

                    posicao++;

                }

                return this;

            }

        });

    }

}
