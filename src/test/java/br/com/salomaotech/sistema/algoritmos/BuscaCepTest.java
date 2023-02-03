package br.com.salomaotech.sistema.algoritmos;

import org.junit.Test;
import static org.junit.Assert.*;

public class BuscaCepTest {

    @Test
    public void testBuscar() {

        BuscaCep cep = new BuscaCep();

        System.out.println("Testando classe BuscaCep metodo: buscar etapa 01");
        assertEquals(true, cep.buscar("07042-090"));

        System.out.println("Testando classe BuscaCep metodo: buscar etapa 02");
        assertEquals(true, cep.buscar("07042090"));

    }

    @Test
    public void testGetLogradouro() {

        BuscaCep cep = new BuscaCep();

        if (cep.buscar("07042-090")) {

            System.out.println("Testando classe BuscaCep metodo: getLogradouro");
            assertEquals(false, cep.getLogradouro().isEmpty());

        }

    }

    @Test
    public void testGetBairro() {

        BuscaCep cep = new BuscaCep();

        if (cep.buscar("07042-090")) {

            System.out.println("Testando classe BuscaCep metodo: getBairro");
            assertEquals(false, cep.getBairro().isEmpty());

        }

    }

    @Test
    public void testGetCidade() {

        BuscaCep cep = new BuscaCep();

        if (cep.buscar("07042-090")) {

            System.out.println("Testando classe BuscaCep metodo: getCidade");
            assertEquals(false, cep.getCidade().isEmpty());

        }

    }

    @Test
    public void testGetUf() {

        BuscaCep cep = new BuscaCep();

        if (cep.buscar("07042-090")) {

            System.out.println("Testando classe BuscaCep metodo: getUf");
            assertEquals(false, cep.getUf().isEmpty());

        }

    }

}
