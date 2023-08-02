package br.com.salomaotech.genesys.controller.novidades;

import br.com.salomaotech.sistema.algoritmos.ApiClient;

public class ExibeNovidades {

    public static void exibir() {

        String apiUrl = "https://taimber.com/api/sistemas/";
        ApiClient.processApiData(apiUrl, new NovidadesMetodos());

    }

}
