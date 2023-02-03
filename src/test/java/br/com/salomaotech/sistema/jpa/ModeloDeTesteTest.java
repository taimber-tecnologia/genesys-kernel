package br.com.salomaotech.sistema.jpa;

import java.util.Calendar;
import static java.util.Objects.isNull;
import org.junit.Test;
import static org.junit.Assert.*;

public class ModeloDeTesteTest {

    private final ModeloDeTeste modeloDeTeste = new ModeloDeTeste();

    @Test
    public void testGetId() {

        System.out.println("Testando classe ModeloDeTeste metodo: getId");
        assertEquals(true, modeloDeTeste.getId() == 0);

    }

    @Test
    public void testSetId() {

        long id = 1;
        modeloDeTeste.setId(id);

        System.out.println("Testando classe ModeloDeTeste metodo: setId");
        assertEquals(true, modeloDeTeste.getId() == id);

    }

    @Test
    public void testGetNome() {

        System.out.println("Testando classe ModeloDeTeste metodo: getNome");
        assertEquals(true, isNull(modeloDeTeste.getNome()));

    }

    @Test
    public void testSetNome() {

        String nome = "Teste";
        modeloDeTeste.setNome(nome);

        System.out.println("Testando classe ModeloDeTeste metodo: setNome");
        assertEquals(true, modeloDeTeste.getNome().equals(nome));

    }

    @Test
    public void testGetIdade() {

        System.out.println("Testando classe ModeloDeTeste metodo: getIdade");
        assertEquals(true, modeloDeTeste.getIdade() == 0);

    }

    @Test
    public void testSetIdade() {

        int idade = 18;
        modeloDeTeste.setIdade(idade);

        System.out.println("Testando classe ModeloDeTeste metodo: setIdade");
        assertEquals(true, modeloDeTeste.getIdade() == idade);

    }

    @Test
    public void testGetNascimento() {

        System.out.println("Testando classe ModeloDeTeste metodo: getNascimento");
        assertEquals(true, isNull(modeloDeTeste.getNascimento()));

    }

    @Test
    public void testSetNascimento() {

        Calendar nascimento = Calendar.getInstance();
        modeloDeTeste.setNascimento(nascimento);

        System.out.println("Testando classe ModeloDeTeste metodo: setNascimento");
        assertEquals(true, modeloDeTeste.getNascimento().equals(nascimento));

    }

    @Test
    public void testGetSegundosDeVida() {

        System.out.println("Testando classe ModeloDeTeste metodo: getSegundosDeVida");
        assertEquals(true, modeloDeTeste.getSegundosDeVida() == 0);

    }

    @Test
    public void testSetSegundosDeVida() {

        long segundosDeVida = Calendar.getInstance().getTimeInMillis();
        modeloDeTeste.setSegundosDeVida(segundosDeVida);

        System.out.println("Testando classe ModeloDeTeste metodo: setSegundosDeVida");
        assertEquals(true, modeloDeTeste.getSegundosDeVida() == segundosDeVida);

    }

}
