package br.com.salomaotech.sistema.modelos;

import static java.util.Objects.isNull;
import org.junit.Test;
import static org.junit.Assert.*;

public class ApiModeloTest {

    private final ApiModelo apiModelo = new ApiModelo();

    @Test
    public void testGetTitulo() {

        System.out.println("Testando classe ApiModelo método: getTitulo");
        assertEquals(true, isNull(apiModelo.getTitulo()));

    }

    @Test
    public void testSetTitulo() {

        apiModelo.setTitulo("Teste");
        System.out.println("Testando classe ApiModelo método: setTitulo");
        assertEquals(true, apiModelo.getTitulo().equals("Teste"));

    }

    @Test
    public void testGetDescricao() {

        System.out.println("Testando classe ApiModelo método: getDescricao");
        assertEquals(true, isNull(apiModelo.getDescricao()));

    }

    @Test
    public void testSetDescricao() {

        apiModelo.setDescricao("teste");
        System.out.println("Testando classe ApiModelo método: setDescricao");
        assertEquals(true, apiModelo.getDescricao().equals("teste"));

    }

    @Test
    public void testGetUrlImagem() {

        System.out.println("Testando classe ApiModelo método: getUrlImagem");
        assertEquals(true, isNull(apiModelo.getUrlImagem()));

    }

    @Test
    public void testSetUrlImagem() {

        System.out.println("Testando classe ApiModelo método: setUrlImagem");
        String urlImagem = "https://example.com/imagem.jpg";
        apiModelo.setUrlImagem(urlImagem);
        System.out.println("URL de imagem definida: " + urlImagem);
        assertEquals(urlImagem, apiModelo.getUrlImagem());

    }

    @Test
    public void testGetLink() {

        System.out.println("Testando classe ApiModelo método: getLink");
        assertEquals(true, isNull(apiModelo.getLink()));

    }

    @Test
    public void testSetLink() {

        System.out.println("Testando classe ApiModelo método: setLink");
        String link = "https://example.com";
        apiModelo.setLink(link);
        System.out.println("URL de link definida: " + link);
        assertEquals(link, apiModelo.getLink());

    }

}
