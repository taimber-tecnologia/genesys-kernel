package br.com.salomaotech.sistema.algoritmos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ArquivoPropriedade {

    private final File file;
    private final Properties properties = new Properties();

    public ArquivoPropriedade(String pathFile) {

        file = new File(pathFile);

    }

    public boolean criar() {

        try {

            new File(file.getParent()).mkdirs();
            file.createNewFile();

        } catch (IOException ex) {

        }

        return file.exists();

    }

    public boolean deletar() {

        return file.delete();

    }

    public String absolutePath() {

        return file.getAbsolutePath();

    }

    public boolean isArquivoConfiguracaoExiste() {

        return file.exists();

    }

    public Properties getProperties() {

        /* 1 - cria a entrada do arquivo */
        FileInputStream fileInputStream = null;

        try {

            /* 2 - tenta ler o arquivo */
            fileInputStream = new FileInputStream(file.getAbsolutePath());
            properties.load(fileInputStream);

        } catch (IOException ex) {

        } finally {

            /* 3 - se o arquivo não for nulo tente fecha-lo */
            if (fileInputStream != null) {

                try {

                    fileInputStream.close();

                } catch (IOException e) {

                }

            }

        }

        return properties;

    }

    public boolean salvar(Properties propriedades) {

        boolean isConseguiuSalvar = false;

        /* 1 - cria o arquivo se ele não existir */
        criar();

        /* 2 - cria a saida do arquivo */
        FileOutputStream fileOutputStream = null;

        /* 3 - tenta salvar o arquivo */
        try {

            fileOutputStream = new FileOutputStream(file.getAbsolutePath());
            propriedades.store(fileOutputStream, null);
            isConseguiuSalvar = true;

        } catch (IOException ex) {

        } finally {

            /* 4 - se o arquivo não for nulo tente fecha-lo */
            if (fileOutputStream != null) {

                try {

                    fileOutputStream.close();

                } catch (IOException e) {

                }

            }

        }

        return isConseguiuSalvar;

    }

}
