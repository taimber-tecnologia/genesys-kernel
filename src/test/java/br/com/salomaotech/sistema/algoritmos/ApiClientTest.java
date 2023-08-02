package br.com.salomaotech.sistema.algoritmos;

import br.com.salomaotech.genesys.controller.novidades.NovidadesMetodos;
import br.com.salomaotech.sistema.modelos.ApiModelo;
import br.com.salomaotech.sistema.patterns.Command;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class ApiClientTest {

    @Test
    public void testProcessApiData() {

        String apiUrl = "https://taimber.com/api/sistemas/";

        /* Criação do objeto de Command NovidadesMetodos usando Mockito.mock() */
        Command novidadesCommand = mock(NovidadesMetodos.class);

        /* Chamada do método a ser testado */
        ApiClient.processApiData(apiUrl, novidadesCommand);

        /* Verifica se o método executar() da classe NovidadesMetodos foi chamado pelo ApiClient */
        verify(novidadesCommand, timeout(5000)).executar(any(ApiModelo.class));

        /* Captura o argumento passado ao método executar() para verificações adicionais se necessário */
        ArgumentCaptor<ApiModelo> argumentCaptor = ArgumentCaptor.forClass(ApiModelo.class);
        verify(novidadesCommand).executar(argumentCaptor.capture());

        /* Aqui, você pode fazer verificações adicionais no objeto capturado se necessário */
        ApiModelo capturado = argumentCaptor.getValue();

        /* Garante que o objeto capturado não seja nulo, por exemplo. */
        assertNotNull(capturado);

    }

}
