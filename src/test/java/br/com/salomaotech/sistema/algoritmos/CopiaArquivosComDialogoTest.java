package br.com.salomaotech.sistema.algoritmos;

import javax.swing.filechooser.FileNameExtensionFilter;
import org.junit.Test;
import static org.junit.Assert.*;

public class CopiaArquivosComDialogoTest {

    private final String pathLocal = System.getProperty("user.dir") + "/target/arquivos/testes_remover/";

    @Test
    public void testCopiar() {

        String pastaDeTeste = pathLocal + "copia_pasta/";

        /* testa se conseguiu copiar apenas um arquivo sem filtro */
        CopiaArquivosComDialogo copiaArquivosComDialogo = new CopiaArquivosComDialogo(pastaDeTeste, false);
        System.out.println("Testando classe CopiaArquivosComDialogo metodo: copiar etapa 01");
        assertEquals(true, copiaArquivosComDialogo.copiar());

        /* testa se conseguiu copiar apenas um arquivo com filtro */
        copiaArquivosComDialogo = new CopiaArquivosComDialogo(pastaDeTeste, false);
        copiaArquivosComDialogo.setFiltro(new FileNameExtensionFilter("Imagens", "jpg", "jpeg", "png"));
        System.out.println("Testando classe CopiaArquivosComDialogo metodo: copiar etapa 02");
        assertEquals(true, copiaArquivosComDialogo.copiar());

        /* testa se conseguiu copiar multiplos arquivos sem filtro */
        copiaArquivosComDialogo = new CopiaArquivosComDialogo(pastaDeTeste, true);
        System.out.println("Testando classe CopiaArquivosComDialogo metodo: copiar etapa 03");
        assertEquals(true, copiaArquivosComDialogo.copiar());

        /* testa se conseguiu copiar multiplos arquivos com filtro */
        copiaArquivosComDialogo = new CopiaArquivosComDialogo(pastaDeTeste, true);
        copiaArquivosComDialogo.setFiltro(new FileNameExtensionFilter("Imagens", "jpg", "jpeg", "png"));
        System.out.println("Testando classe CopiaArquivosComDialogo metodo: copiar etapa 04");
        assertEquals(true, copiaArquivosComDialogo.copiar());

    }

    @Test
    public void testGetListaDeArquivosCopiadoscopiar() {

        String pastaDeTeste = pathLocal + "copia_pasta/";

        /* testa se conseguiu copiar apenas um arquivo sem filtro */
        CopiaArquivosComDialogo copiaArquivosComDialogo = new CopiaArquivosComDialogo(pastaDeTeste, false);
        copiaArquivosComDialogo.copiar();
        System.out.println("Testando classe CopiaArquivosComDialogo metodo: getListaDeArquivosCopiadoscopiar etapa 01");
        assertEquals(true, copiaArquivosComDialogo.getListaDeArquivosCopiados().size() == 1);

        /* testa se conseguiu copiar apenas um arquivo com filtro */
        copiaArquivosComDialogo = new CopiaArquivosComDialogo(pastaDeTeste, false);
        copiaArquivosComDialogo.setFiltro(new FileNameExtensionFilter("Imagens", "jpg", "jpeg", "png"));
        copiaArquivosComDialogo.copiar();
        System.out.println("Testando classe CopiaArquivosComDialogo metodo: getListaDeArquivosCopiadoscopiar etapa 02");
        assertEquals(true, copiaArquivosComDialogo.getListaDeArquivosCopiados().size() == 1);

        /* testa se conseguiu copiar multiplos arquivos sem filtro */
        copiaArquivosComDialogo = new CopiaArquivosComDialogo(pastaDeTeste, true);
        copiaArquivosComDialogo.copiar();
        System.out.println("Testando classe CopiaArquivosComDialogo metodo: getListaDeArquivosCopiadoscopiar etapa 03");
        assertEquals(true, copiaArquivosComDialogo.getListaDeArquivosCopiados().size() >= 1);

        /* testa se conseguiu copiar multiplos arquivos com filtro */
        copiaArquivosComDialogo = new CopiaArquivosComDialogo(pastaDeTeste, true);
        copiaArquivosComDialogo.setFiltro(new FileNameExtensionFilter("Imagens", "jpg", "jpeg", "png"));
        copiaArquivosComDialogo.copiar();
        System.out.println("Testando classe CopiaArquivosComDialogo metodo: getListaDeArquivosCopiadoscopiar etapa 04");
        assertEquals(true, copiaArquivosComDialogo.getListaDeArquivosCopiados().size() >= 1);

    }

}
