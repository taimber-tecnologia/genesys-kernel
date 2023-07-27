package br.com.salomaotech.genesys.controller.ativador;

import br.com.salomaotech.genesys.view.JFativador;
import br.com.salomaotech.sistema.algoritmos.ExecutaProgramaExterno;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

public class AtivadorEventos {

    private final JFativador view;
    private AtivadorMetodos ativadorMetodos;

    public AtivadorEventos(JFativador view) {
        this.view = view;
    }

    public void setAtivadorMetodos(AtivadorMetodos ativadorMetodos) {
        this.ativadorMetodos = ativadorMetodos;
    }

    public void addEventos() {

        /* botão ativar */
        view.jBativar.addActionListener((ActionEvent e) -> {

            /* valida o campo */
            if (new AtivadorValidador(view).isValido()) {

                if (ativadorMetodos.isAtivar(view.jTchave.getText())) {

                    JOptionPane.showMessageDialog(null, "Sistema ativado com sucesso! Muito obrigado.");
                    JOptionPane.showMessageDialog(null, "Feche o sistema e abra-o novamente.");
                    view.dispose();

                } else {

                    JOptionPane.showMessageDialog(null, "Chave inválida!");

                }

            } else {

                JOptionPane.showMessageDialog(null, "Chave não informada!");
                view.jTchave.requestFocus();

            }

        });

        /* atalho para suporte */
        view.jLabrirSuporte.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

                ExecutaProgramaExterno.abreUrlNoBrowser("https://www.taimber.com/");

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });

    }

}
