package br.com.salomaotech.sistema.algoritmos;

import br.com.salomaotech.sistema.jpa.ConfiguracoesConexao;
import java.io.File;
import java.util.Properties;
import org.junit.Test;
import static org.junit.Assert.*;

public class ArquivoPropriedadeTest {

    private final Properties propriedades;
    private final ArquivoPropriedade arquivoPropriedade;
    private final File testeFile;

    public ArquivoPropriedadeTest() {

        /* popula propriedades */
        propriedades = new Properties();
        propriedades.put("chk_1", "123");
        propriedades.put("chk_2", "456");

        /* salva as propriedades */
        testeFile = new File(new File(new ConfiguracoesConexao().getPathArquivo()).getParent() + "/propriedade_xxxxxx.txt");
        arquivoPropriedade = new ArquivoPropriedade(testeFile.getAbsolutePath());
        arquivoPropriedade.salvar(propriedades);

    }

    @Test
    public void testCriar() {

        System.out.println("Testando classe ArquivoPropriedade metodo: criar");
        assertEquals(true, arquivoPropriedade.criar());

    }

    @Test
    public void testDeletar() {

        System.out.println("Testando classe ArquivoPropriedade metodo: deletar");
        assertEquals(true, arquivoPropriedade.deletar());

    }

    @Test
    public void testAbsolutePath() {

        System.out.println("Testando classe ArquivoPropriedade metodo: absolutePath");
        assertEquals(true, arquivoPropriedade.absolutePath().equals(testeFile.getAbsolutePath()));

    }

    @Test
    public void testIsArquivoConfiguracaoExiste() {

        System.out.println("Testando classe ArquivoPropriedade metodo: isArquivoConfiguracaoExiste");
        assertEquals(true, arquivoPropriedade.isArquivoConfiguracaoExiste());

    }

    @Test
    public void testGetProperties() {

        Properties propriedadesComparar = arquivoPropriedade.getProperties();
        System.out.println("Testando classe ArquivoPropriedade metodo: getProperties");
        assertEquals(true, propriedadesComparar.get("chk_1").equals(propriedades.get("chk_1")));
        assertEquals(true, propriedadesComparar.get("chk_2").equals(propriedades.get("chk_2")));

    }

    @Test
    public void testSalvar() {

        System.out.println("Testando classe ArquivoPropriedade metodo: salvar");
        assertEquals(true, arquivoPropriedade.salvar(propriedades));

    }

}
