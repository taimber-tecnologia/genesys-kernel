package br.com.salomaotech.sistema.algoritmos;

import org.junit.Test;
import static org.junit.Assert.*;

public class RemovePastaDeArquivosTest {

    private final String pathLocal = System.getProperty("user.dir") + "/target/arquivos/testes_remover/";

    @Test
    public void testRemover() {

        String pastaDeTeste = pathLocal + "teste_exclusao/";

        for (int i = 0; i <= 10; i++) {

            CriaPastaLocal.criar(pastaDeTeste + i);

        }

        System.out.println("Testando classe RemovePastaDeArquivos metodo: remover");
        assertEquals(true, RemovePastaDeArquivos.remover(pastaDeTeste));

    }

}
