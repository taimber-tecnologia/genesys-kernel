package br.com.salomaotech.sistema.algoritmos;

import java.awt.Component;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import static java.util.Objects.isNull;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ImagemExibe {

    private final JPanel painel;
    private final int largura;
    private final int altura;

    public ImagemExibe(JPanel painel, int largura, int altura) {
        this.painel = painel;
        this.largura = largura;
        this.altura = altura;
    }

    private void addEvento(Component elemento, String pathImagem) {

        elemento.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                if (e.getClickCount() == 2 && !e.isConsumed()) {

                    ExecutaProgramaExterno.executarModoDesktop(pathImagem);

                }

            }

        });

    }

    /**
     * Exibe a imagem
     *
     * @param pathImagem Path da localizacao da imagem
     * @return retorna true se conseguiu exibir a imagem
     */
    public boolean exibir(String pathImagem) {

        if (isNull(pathImagem)) {

            return false;

        }

        Image imagem;

        /* se não conseguir usar URL como path tente usar como String */
        try {

            imagem = new ImageIcon(new URL(pathImagem)).getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);

        } catch (MalformedURLException ex) {

            if (new File(pathImagem).exists()) {

                imagem = new ImageIcon(pathImagem).getImage().getScaledInstance(largura, altura, Image.SCALE_SMOOTH);

            } else {

                return false;

            }

        }

        JLabel elemento = new JLabel(new ImageIcon(imagem));
        elemento.setLocation(5, 5);
        elemento.setSize(largura, altura);
        painel.removeAll();
        painel.add(elemento);
        painel.repaint();
        addEvento(elemento, pathImagem);
        return true;

    }

}
