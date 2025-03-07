package br.com.salomaotech.sistema.algoritmos;

import org.json.JSONObject;
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
     * @return true se conseguiu buscar o CEP com sucesso
     */
    public boolean buscar(String cep) {

        try {

            URL url = new URL("https://viacep.com.br/ws/" + cep + "/json");
            URLConnection urlConnection = url.openConnection();

            try (InputStream is = urlConnection.getInputStream(); BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {

                StringBuilder jsonSb = new StringBuilder();
                br.lines().forEach(jsonSb::append);

                // Converte a string JSON para um objeto JSONObject
                JSONObject json = new JSONObject(jsonSb.toString());

                // Verifica se o CEP é inválido (a API retorna "erro": true)
                if (json.has("erro") && json.getBoolean("erro")) {

                    return false;

                }

                // Acessa os dados diretamente do JSON
                logradouro = json.optString("logradouro", "");
                bairro = json.optString("bairro", "");
                cidade = json.optString("localidade", "");
                uf = json.optString("uf", "");

                return true;

            }

        } catch (IOException e) {

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
