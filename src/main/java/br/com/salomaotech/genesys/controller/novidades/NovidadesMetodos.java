package br.com.salomaotech.genesys.controller.novidades;

import br.com.salomaotech.sistema.modelos.ApiModelo;
import br.com.salomaotech.genesys.view.JFnovidades;
import br.com.salomaotech.sistema.algoritmos.ExecutaProgramaExterno;
import br.com.salomaotech.sistema.patterns.Command;
import br.com.salomaotech.sistema.swing.MudaIconeJframe;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class NovidadesMetodos implements Command {

    public void exibirImagem(String urlImagem, JLabel label) {

        try {

            URL url = new URL(urlImagem);
            Image image = ImageIO.read(url);

            // Define a largura e altura máximas para o redimensionamento da imagem
            int maxWidth = label.getWidth();
            int maxHeight = label.getHeight();

            // Obtém as dimensões da imagem original
            int imageWidth = image.getWidth(null);
            int imageHeight = image.getHeight(null);

            // Calcula a proporção para redimensionar a imagem
            double scaleFactor = Math.min(1.0 * maxWidth / imageWidth, 1.0 * maxHeight / imageHeight);

            // Calcula as novas dimensões da imagem redimensionada
            int newWidth = (int) (imageWidth * scaleFactor);
            int newHeight = (int) (imageHeight * scaleFactor);

            // Redimensiona a imagem para as novas dimensões
            Image scaledImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

            // Cria um novo ImageIcon com a imagem redimensionada
            ImageIcon icon = new ImageIcon(scaledImage);

            // Define o novo ImageIcon no JLabel
            label.setIcon(icon);

            // Centraliza a imagem no JLabel
            int x = (maxWidth - newWidth) / 2;
            int y = (maxHeight - newHeight) / 2;

            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setVerticalAlignment(SwingConstants.CENTER);
            label.setBounds(x, y, newWidth, newHeight);

        } catch (IOException e) {

        }

    }

    public void addEventos(JFnovidades view, String urlLink) {

        view.jBbotaoConhecer.addActionListener((ActionEvent e) -> {

            ExecutaProgramaExterno.abreUrlNoBrowser(urlLink);

        });

        view.jBbotaoFechar.addActionListener((ActionEvent e) -> {

            view.dispose();

        });

    }

    @Override
    public void executar(Object arg) {

        /* view */
        JFnovidades view = new JFnovidades();
        new MudaIconeJframe().alterar("news24x", view);

        /* api */
        ApiModelo api = (ApiModelo) arg;

        /* exibe dados */
        view.jLtitulo.setText(api.getTitulo());
        view.jLdescricao.setText(api.getDescricao());
        exibirImagem(api.getUrlImagem(), view.jLimagem);

        /* adiciona eventos */
        addEventos(view, api.getLink());

        /* exibe a view */
        view.setVisible(true);

    }

}
