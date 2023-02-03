package br.com.salomaotech.sistema.algoritmos;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExecutaProgramaExterno {

    /**
     * Executa um programa externo no modo desktop
     *
     * @param enderecoDoPrograma Endereço do programa
     * @return True conseguiu executar
     */
    public static boolean executarModoDesktop(String enderecoDoPrograma) {

        try {

            /* valida se o programa existe */
            if (new File(enderecoDoPrograma).exists()) {

                Desktop desktop = Desktop.getDesktop();
                File meuArquivo = new File(enderecoDoPrograma);
                desktop.open(meuArquivo);

                return true;

            } else {

                return false;

            }

        } catch (IOException | java.lang.NullPointerException ex) {

            return false;

        }

    }

    /**
     * Executa um programa externo no formato .jar
     *
     * @param enderecoDoPrograma Endereço do programa
     * @param parametro Parâmetros
     * @return true conseguiu executar o programa
     */
    public static boolean executarModoJar(String enderecoDoPrograma, Object parametro) {

        try {

            /* valida se o programa existe */
            if (new File(enderecoDoPrograma).exists()) {

                Path pastaDoPrograma = Paths.get(enderecoDoPrograma);
                File arquivo = new File(pastaDoPrograma.getParent().toString());
                String comandoExecutar = "java -jar " + pastaDoPrograma.getFileName() + " " + parametro;
                Runtime.getRuntime().exec(comandoExecutar, null, arquivo);

                return true;

            } else {

                return false;

            }

        } catch (IOException | java.lang.NullPointerException ex) {

            return false;

        }

    }

    /**
     * Abre uma URL de site
     *
     * @param url URL
     * @return
     */
    public static boolean abreUrlNoBrowser(String url) {

        try {

            Desktop.getDesktop().browse(new URI(url));
            return true;

        } catch (IOException | URISyntaxException ex) {

            return false;

        }

    }

}
