package br.com.salomaotech.sistema.jpa;

import static java.util.Objects.isNull;
import org.junit.Test;
import static org.junit.Assert.*;

public class ConexaoSingletonTest {

    private final ConexaoSingleton conexaoSingleton = new ConexaoSingleton();

    @Test
    public void testAbrirConexao() {

        conexaoSingleton.abrirConexao("Conexao");
        System.out.println("Testando classe ConexaoSingleton metodo: abrirConexao");
        assertEquals(true, conexaoSingleton.isConexaoAberta());

    }

    @Test
    public void testFecharConexao() {

        conexaoSingleton.abrirConexao("Conexao");
        conexaoSingleton.fecharConexao();

        System.out.println("Testando classe ConexaoSingleton metodo: fecharConexao");
        assertEquals(false, conexaoSingleton.isConexaoAberta());

    }

    @Test
    public void testIsConexaoAberta() {

        conexaoSingleton.abrirConexao("Conexao");
        System.out.println("Testando classe ConexaoSingleton metodo: isConexaoAberta etapa 01");
        assertEquals(true, conexaoSingleton.isConexaoAberta());

        conexaoSingleton.fecharConexao();
        System.out.println("Testando classe ConexaoSingleton metodo: isConexaoAberta etapa 02");
        assertEquals(false, conexaoSingleton.isConexaoAberta());

    }

    @Test
    public void testGetManager() {

        conexaoSingleton.abrirConexao("Conexao");
        System.out.println("Testando classe ConexaoSingleton metodo: getEntityManager etapa 01");
        assertEquals(false, isNull(conexaoSingleton.getEntityManager()));

        conexaoSingleton.fecharConexao();
        System.out.println("Testando classe ConexaoSingleton metodo: getEntityManager etapa 02");
        assertEquals(true, isNull(conexaoSingleton.getEntityManager()));

    }

}
