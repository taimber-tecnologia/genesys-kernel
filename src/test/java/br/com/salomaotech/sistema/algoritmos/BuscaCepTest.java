package br.com.salomaotech.sistema.algoritmos;

import org.junit.Test;
import static org.junit.Assert.*;

public class BuscaCepTest {

    @Test
    public void testBuscar() {

        BuscaCep cep = new BuscaCep();

        System.out.println("Testando classe BuscaCep metodo: buscar etapa 01");
        assertTrue(cep.buscar("07042-090"));

        System.out.println("Testando classe BuscaCep metodo: buscar etapa 02");
        assertTrue(cep.buscar("07042090"));

    }

    @Test
    public void testGetLogradouro() {

        BuscaCep cep = new BuscaCep();
        cep.buscar("07042-090");

        System.out.println("Testando classe BuscaCep metodo: getLogradouro");
        assertEquals("Rua Canadense", cep.getLogradouro());

    }

    @Test
    public void testGetBairro() {

        BuscaCep cep = new BuscaCep();
        cep.buscar("07042-090");

        System.out.println("Testando classe BuscaCep metodo: getBairro");
        assertEquals("Vila Flora", cep.getBairro());

    }

    @Test
    public void testGetCidade() {

        BuscaCep cep = new BuscaCep();
        cep.buscar("07042-090");

        System.out.println("Testando classe BuscaCep metodo: getCidade");
        assertEquals("Guarulhos", cep.getCidade());

    }

    @Test
    public void testGetUf() {

        BuscaCep cep = new BuscaCep();
        cep.buscar("07042-090");

        System.out.println("Testando classe BuscaCep metodo: getUf");
        assertEquals("SP", cep.getUf());

    }

}
