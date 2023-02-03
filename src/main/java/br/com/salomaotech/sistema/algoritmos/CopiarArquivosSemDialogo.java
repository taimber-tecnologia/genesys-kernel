package br.com.salomaotech.sistema.algoritmos;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class CopiarArquivosSemDialogo {

    private static boolean conseguiuCopiar;

    /**
     * Copia arquivos de origem para destino
     *
     * @param pathOrigem Path de origem
     * @param pathDestino Path de destino
     * @return true conseguiu copiar todos os arquivos
     */
    public static boolean copiar(String pathOrigem, String pathDestino) {

        Path origemArquivos = Paths.get(pathOrigem);
        Path destinoArquivos = Paths.get(pathDestino);
        conseguiuCopiar = false;

        try {

            Files.walk(origemArquivos).forEach(origem -> {

                try {

                    Path destino = destinoArquivos.resolve(origemArquivos.relativize(origem));
                    Files.copy(origem, destino, StandardCopyOption.REPLACE_EXISTING);
                    conseguiuCopiar = true;

                } catch (IOException ex) {

                    conseguiuCopiar = false;

                }

            });

        } catch (IOException ex) {

            conseguiuCopiar = false;

        }

        return conseguiuCopiar;

    }

}
