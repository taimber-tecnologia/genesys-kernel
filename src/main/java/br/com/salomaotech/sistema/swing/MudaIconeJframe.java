package br.com.salomaotech.sistema.swing;

import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class MudaIconeJframe {

    /**
     * Altera o icone de um JFrame
     *
     * @param nomeIcone nome do ícone
     * @param view JFrame
     * @return boolean em caso de não houver falha
     */
    public boolean alterar(String nomeIcone, JFrame view) {

        try {

            /* carrega novo ImageIcon */
            ImageIcon imageIcon = new ImageIcon(getClass().getResource("/icones/" + nomeIcone + ".png"));
            Image image = imageIcon.getImage();

            /* seta ImageIcon como icone da JFrame */
            view.setIconImage(image);

            return true;

        } catch (Exception ex) {

            return false;

        }

    }

}
