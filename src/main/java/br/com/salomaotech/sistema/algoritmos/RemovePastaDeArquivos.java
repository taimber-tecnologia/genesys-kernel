package br.com.salomaotech.sistema.algoritmos;

import java.io.File;

public class RemovePastaDeArquivos {

    /**
     * Remove uma pasta e todos os seus arquivos interiores
     *
     * @param pathDestino Path de destino
     * @return true conseguiu a pasta com seus arquivos
     */
    public static boolean remover(String pathDestino) {

        try {

            /* lista os arquivos dentro do diretório */
            File[] listaDeArquivos = new File(pathDestino).listFiles();

            /* itera e deleta o arquivo encontrado */
            for (File arquivo : listaDeArquivos) {

                arquivo.delete();

            }

            /* remove a path de destino */
            new File(pathDestino).delete();

            /* retorna se o path existe ainda */
            return !new File(pathDestino).exists();

        } catch (Exception ex) {

            /* retorna se o path existe ainda */
            return !new File(pathDestino).exists();

        }

    }

}
