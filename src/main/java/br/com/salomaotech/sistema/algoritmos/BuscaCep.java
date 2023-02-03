package br.com.salomaotech.sistema.algoritmos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;

public class BuscaCep {

    private String logradouro;
    private String bairro;
    private String cidade;
    private String uf;

    /**
     * Busca por dados de um CEP
     *
     * @param cep Número do CEP
     * @return true conseguiu buscar o CEP
     */
    public boolean buscar(String cep) {

        String json;

        try {

            URL url = new URL("https://viacep.com.br/ws/" + cep + "/json");
            URLConnection urlConnection = url.openConnection();
            BufferedReader br;
            String[] arrayRetorno;

            /* pega os dados da conexão */
            try (InputStream is = urlConnection.getInputStream()) {

                /* buffer */
                br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
                StringBuilder jsonSb = new StringBuilder();

                /* lê as linhas */
                br.lines().forEach(l -> jsonSb.append(l.trim()));
                json = jsonSb.toString();

                /* pega os dados */
                json = json.replaceAll("[{},:]", "");
                json = json.replaceAll("\"", "\n");
                arrayRetorno = new String[30];
                arrayRetorno = json.split("\n");

                /* popula dados */
                logradouro = arrayRetorno[7];
                bairro = arrayRetorno[15];
                cidade = arrayRetorno[19];
                uf = arrayRetorno[23];

            }

            /* fecha tudo o que estiver aberto */
            br.close();

            return true;

        } catch (IOException | ArrayIndexOutOfBoundsException e) {

            return false;

        }

    }

    public String getLogradouro() {
        return logradouro;
    }

    public String getBairro() {
        return bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public String getUf() {
        return uf;
    }

}
