package br.com.salomaotech.sistema.algoritmos;

import java.io.File;

public class CaminhoArquivoNormalizado {

    public static String obterCaminhoArquivoNormalizado(String path) {

        if (!path.endsWith(File.separator)) {

            path = new File(path).getPath() + File.separator;

        }

        return path;

    }

}
