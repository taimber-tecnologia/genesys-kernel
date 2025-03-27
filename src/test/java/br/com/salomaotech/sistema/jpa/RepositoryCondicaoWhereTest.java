package br.com.salomaotech.sistema.jpa;

import static org.junit.Assert.*;

import org.junit.Test;

public class RepositoryCondicaoWhereTest {

    @Test
    public void testGetOperador() {

        // Testando operadores diversos
        RepositoryCondicaoWhere condicaoIgual = new RepositoryCondicaoWhere("=", 10);
        RepositoryCondicaoWhere condicaoMenor = new RepositoryCondicaoWhere("<", 5);
        RepositoryCondicaoWhere condicaoMaior = new RepositoryCondicaoWhere(">", 15);
        RepositoryCondicaoWhere condicaoMenorIgual = new RepositoryCondicaoWhere("<=", 8);
        RepositoryCondicaoWhere condicaoMaiorIgual = new RepositoryCondicaoWhere(">=", 12);
        RepositoryCondicaoWhere condicaoDiferente = new RepositoryCondicaoWhere("!=", 7);

        // Testando operadores IS NULL e IS NOT NULL
        RepositoryCondicaoWhere condicaoIsNull = new RepositoryCondicaoWhere("IS NULL", null);
        RepositoryCondicaoWhere condicaoIsNotNull = new RepositoryCondicaoWhere("IS NOT NULL", null);

        // Testando operador "=" com valor null
        RepositoryCondicaoWhere condicaoValorNull = new RepositoryCondicaoWhere("=", null);

        // Testando operadores com valor null
        RepositoryCondicaoWhere condicaoMaiorQueNull = new RepositoryCondicaoWhere(">", null);
        RepositoryCondicaoWhere condicaoMenorQueNull = new RepositoryCondicaoWhere("<", null);

        System.out.println("Testando classe RepositoryCondicaoWhere método: getOperador");
        assertEquals("=", condicaoIgual.getOperador());
        assertEquals("<", condicaoMenor.getOperador());
        assertEquals(">", condicaoMaior.getOperador());
        assertEquals("<=", condicaoMenorIgual.getOperador());
        assertEquals(">=", condicaoMaiorIgual.getOperador());
        assertEquals("!=", condicaoDiferente.getOperador());
        assertEquals("IS NULL", condicaoIsNull.getOperador());
        assertEquals("IS NOT NULL", condicaoIsNotNull.getOperador());
        assertEquals("=", condicaoValorNull.getOperador());
        assertEquals(">", condicaoMaiorQueNull.getOperador());
        assertEquals("<", condicaoMenorQueNull.getOperador());

    }

    @Test
    public void testGetValor() {

        // Testando valores diversos
        RepositoryCondicaoWhere condicaoIgual = new RepositoryCondicaoWhere("=", 10);
        RepositoryCondicaoWhere condicaoMenor = new RepositoryCondicaoWhere("<", 5);
        RepositoryCondicaoWhere condicaoMaior = new RepositoryCondicaoWhere(">", 15);
        RepositoryCondicaoWhere condicaoMenorIgual = new RepositoryCondicaoWhere("<=", 8);
        RepositoryCondicaoWhere condicaoMaiorIgual = new RepositoryCondicaoWhere(">=", 12);
        RepositoryCondicaoWhere condicaoDiferente = new RepositoryCondicaoWhere("!=", 7);

        // Testando valores para IS NULL e IS NOT NULL
        RepositoryCondicaoWhere condicaoIsNull = new RepositoryCondicaoWhere("IS NULL", null);
        RepositoryCondicaoWhere condicaoIsNotNull = new RepositoryCondicaoWhere("IS NOT NULL", null);

        // Testando valor null com operador "=" (deve ajustar para IS NULL)
        RepositoryCondicaoWhere condicaoValorNull = new RepositoryCondicaoWhere("=", null);

        // Testando valores null com outros operadores
        RepositoryCondicaoWhere condicaoMaiorQueNull = new RepositoryCondicaoWhere(">", null);
        RepositoryCondicaoWhere condicaoMenorQueNull = new RepositoryCondicaoWhere("<", null);

        System.out.println("Testando classe RepositoryCondicaoWhere método: getValor");
        assertEquals(10, condicaoIgual.getValor());
        assertEquals(5, condicaoMenor.getValor());
        assertEquals(15, condicaoMaior.getValor());
        assertEquals(8, condicaoMenorIgual.getValor());
        assertEquals(12, condicaoMaiorIgual.getValor());
        assertEquals(7, condicaoDiferente.getValor());
        assertEquals("IS NULL", condicaoIsNull.getValor());
        assertEquals("IS NOT NULL", condicaoIsNotNull.getValor());
        assertEquals("IS NULL", condicaoValorNull.getValor());
        assertEquals("IS NULL", condicaoMaiorQueNull.getValor());
        assertEquals("IS NULL", condicaoMenorQueNull.getValor());

    }

}
