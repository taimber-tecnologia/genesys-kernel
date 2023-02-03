package br.com.salomaotech.sistema.swing;

import java.awt.Component;
import java.awt.Toolkit;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import static java.util.Objects.isNull;
import javax.swing.JFormattedTextField;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.text.JTextComponent;

public class PopUp {

    private JPopupMenu pMenu;
    private Component componenteAtivo;
    private JMenuItem jMenuItemRecortar;
    private JMenuItem jMenuItemCopiar;
    private JMenuItem jMenuItemColar;
    private JMenuItem jMenuItemLimpar;

    /**
     * Adiciona PopUp a um component
     *
     * @param componente Component
     */
    public void adicionarMenu(Component componente) {

        /* novo JPopupMenu */
        pMenu = new JPopupMenu();

        jMenuItemRecortar = new JMenuItem("Recortar");
        pMenu.add(jMenuItemRecortar);
        jMenuItemRecortar.addActionListener((ActionEvent arg0) -> {

            JTextComponent c = (JTextComponent) componenteAtivo;
            c.cut();

        });

        jMenuItemCopiar = new JMenuItem("Copiar");
        pMenu.add(new JSeparator());
        pMenu.add(jMenuItemCopiar);
        jMenuItemCopiar.addActionListener((ActionEvent arg0) -> {

            JTextComponent c = (JTextComponent) componenteAtivo;
            c.copy();

        });

        jMenuItemColar = new JMenuItem("Colar");
        pMenu.add(new JSeparator());
        pMenu.add(jMenuItemColar);
        jMenuItemColar.addActionListener((ActionEvent arg0) -> {

            JTextComponent c = (JTextComponent) componenteAtivo;
            c.paste();

        });

        jMenuItemLimpar = new JMenuItem("Limpar");
        pMenu.add(new JSeparator());
        pMenu.add(jMenuItemLimpar);
        jMenuItemLimpar.addActionListener((ActionEvent arg0) -> {

            /* campo de texto comum */
            JTextComponent c = (JTextComponent) componenteAtivo;
            c.setText(null);

            try {

                /* campo de texto formatado */
                JFormattedTextField d = (JFormattedTextField) componenteAtivo;
                d.setValue(null);

            } catch (Exception ex) {

            }

        });

        componente.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {

                if (e.getButton() == 3) {

                    /* move o foco */
                    componente.requestFocus();

                    /* seta o componente e exibe o menu adicionado */
                    componenteAtivo = componente;
                    pMenu.show(componente, e.getX(), e.getY());

                    /* habilita opções do menu */
                    JTextComponent c = (JTextComponent) componente;
                    jMenuItemRecortar.setEnabled(!isNull(c.getSelectedText()));
                    jMenuItemCopiar.setEnabled(!isNull(c.getSelectedText()));
                    jMenuItemColar.setEnabled(isStringNaClipBoard());

                    /* move o foco */
                    componente.requestFocus();

                }

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }

        });

    }

    private boolean isStringNaClipBoard() {

        try {

            return !isNull(Toolkit.getDefaultToolkit().getSystemClipboard().getContents(this).getTransferData(DataFlavor.stringFlavor));

        } catch (UnsupportedFlavorException | IOException ex) {

            return false;

        }

    }

    /**
     * Retorna se há um JPopupMenu associado a um componente
     *
     * @param componente
     * @return
     */
    public boolean isMenuPopUpAdicionado(Component componente) {

        for (MouseListener classe : componente.getMouseListeners()) {

            if (classe.getClass().toString().contains("swing.PopUp")) {

                return true;

            }

        }

        return false;

    }

}
