package br.com.salomaotech.sistema.algoritmos;

import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;

public class CaminhoArquivoNormalizadoTest {

    @Test
    public void testObterCaminhoArquivoNormalizado() {

        String path = CaminhoArquivoNormalizado.obterCaminhoArquivoNormalizado(System.getProperty("user.dir"));
        assertEquals(true, path.endsWith(File.separator));

    }

}
