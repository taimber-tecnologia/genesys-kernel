package br.com.salomaotech.sistema.algoritmos;

import org.junit.Test;
import static org.junit.Assert.*;

public class ImagemRedimensionaTest {

    private final String pathLocal = System.getProperty("user.dir") + "/target/arquivos/testes_remover/";

    @Test
    public void testRedimensionar() {

        String pastaDeTeste = pathLocal + "redimensiona_imagem/saida.png";
        CriaPastaLocal.criar(pastaDeTeste);

        System.out.println("Testando classe ImagemRedimensiona metodo: redimensionar");
        assertEquals(true, new ImagemRedimensiona("src/main/resources/icones/gato.png", pastaDeTeste, 50).redimensionar());

    }

}
