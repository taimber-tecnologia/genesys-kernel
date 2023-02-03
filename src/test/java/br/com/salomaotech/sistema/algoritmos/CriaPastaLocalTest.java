package br.com.salomaotech.sistema.algoritmos;

import org.junit.Test;
import static org.junit.Assert.*;

public class CriaPastaLocalTest {

    private final String pathLocal = System.getProperty("user.dir") + "/target/arquivos/testes_remover/";

    @Test
    public void testCriar() {

        String pastaDeTeste = pathLocal + "cria_pasta/";
        RemovePastaDeArquivos.remover(pastaDeTeste);

        System.out.println("Testando classe CriaPastaLocal metodo: criar");
        assertEquals(true, CriaPastaLocal.criar(pastaDeTeste));

    }

}
