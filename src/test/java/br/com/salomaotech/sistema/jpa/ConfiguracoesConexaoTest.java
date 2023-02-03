package br.com.salomaotech.sistema.jpa;

import java.io.File;
import static java.util.Objects.isNull;
import org.junit.Test;
import static org.junit.Assert.*;

public class ConfiguracoesConexaoTest {

    private final ConfiguracoesConexao configuracoesConexao = new ConfiguracoesConexao();

    @Test
    public void testGetPropriedades() {

        System.out.println("Testando classe ConfiguracoesConexao metodo: getPropriedades");
        assertEquals(true, isNull(configuracoesConexao.getPropriedades()));

    }

    @Test
    public void testGetPathArquivoConfiguracao() {

        File file = new File(System.getProperty("user.dir") + "/target/config/rede.txt");
        assertEquals(true, configuracoesConexao.getPathArquivo().equals(file.getAbsolutePath()));

    }

}
