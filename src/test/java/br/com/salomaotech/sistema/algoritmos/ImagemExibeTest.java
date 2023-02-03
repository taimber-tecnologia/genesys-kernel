package br.com.salomaotech.sistema.algoritmos;

import javax.swing.JPanel;
import org.junit.Test;
import static org.junit.Assert.*;

public class ImagemExibeTest {

    @Test
    public void testExibir() {

        System.out.println("Testando classe ImagemExibe metodo: exibir etapa 01 arquivo não existe");
        assertEquals(false, new ImagemExibe(new JPanel(), 125, 125).exibir("src/main/resources/icones/gatoxxx.png"));

        System.out.println("Testando classe ImagemExibe metodo: exibir etapa 02 endereço de arquivo nulo");
        assertEquals(false, new ImagemExibe(new JPanel(), 125, 125).exibir(null));

        System.out.println("Testando classe ImagemExibe metodo: exibir etapa 03 tudo certo");
        assertEquals(true, new ImagemExibe(new JPanel(), 125, 125).exibir("src/main/resources/icones/gato.png"));

    }

}
