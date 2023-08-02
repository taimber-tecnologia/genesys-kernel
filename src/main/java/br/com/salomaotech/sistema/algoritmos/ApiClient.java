package br.com.salomaotech.sistema.algoritmos;

import br.com.salomaotech.sistema.modelos.ApiModelo;
import br.com.salomaotech.sistema.patterns.Command;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import java.io.IOException;
import javax.swing.*;

public class ApiClient {

    public static void processApiData(String apiUrl, Command command) {

        OkHttpClient httpClient = new OkHttpClient();
        Request request = new Request.Builder().url(apiUrl).build();

        httpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {

                httpClient.dispatcher().executorService().shutdown();

            }

            @Override
            public void onResponse(Call call, Response response) {

                try (ResponseBody responseBody = response.body()) {

                    ApiModelo api = new ObjectMapper().readValue(responseBody.string(), ApiModelo.class);

                    SwingUtilities.invokeLater(() -> {

                        /* exibe os dados */
                        command.executar(api);

                        httpClient.dispatcher().executorService().shutdown();

                    });

                } catch (IOException e) {

                    httpClient.dispatcher().executorService().shutdown();

                }

            }

        });

    }

}
