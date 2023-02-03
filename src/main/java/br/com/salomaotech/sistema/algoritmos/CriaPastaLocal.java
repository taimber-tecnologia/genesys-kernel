package br.com.salomaotech.sistema.algoritmos;

import java.io.File;

public class CriaPastaLocal {

    /**
     * Cria uma pasta local
     *
     * @param pathDestino Path de destino
     * @return true conseguiu criar a pasta
     */
    public static boolean criar(String pathDestino) {

        try {

            return new File(pathDestino).mkdirs();

        } catch (Exception ex) {

            return false;

        }

    }

}
