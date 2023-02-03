package br.com.salomaotech.sistema.algoritmos;

import org.junit.Test;
import static org.junit.Assert.*;

public class CopiarArquivosSemDialogoTest {

    private final String pathLocal = System.getProperty("user.dir") + "/target/arquivos/testes_remover/";

    @Test
    public void testCopiar() {

        String pastaDeTesteOrigem = pathLocal + "teste_copia/pasta_a/";
        String pastaDeTesteDestino = pathLocal + "teste_copia/pasta_b/";

        for (int i = 0; i <= 10; i++) {

            CriaPastaLocal.criar(pastaDeTesteOrigem + "/" + i);

        }

        System.out.println("Testando classe CopiarArquivosSemDialogo metodo: copiar");
        assertEquals(true, CopiarArquivosSemDialogo.copiar(pastaDeTesteOrigem, pastaDeTesteDestino));

    }

}
