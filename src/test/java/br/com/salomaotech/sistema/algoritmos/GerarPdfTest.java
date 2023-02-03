package br.com.salomaotech.sistema.algoritmos;

import org.junit.Test;
import static org.junit.Assert.*;

public class GerarPdfTest {

    private final String pathLocal = System.getProperty("user.dir") + "/target/arquivos/testes_remover/";

    @Test
    public void testGerarPdf() {

        String pastaDeTeste = pathLocal + "gera_pdf/";
        GerarPdf pdf = new GerarPdf(pastaDeTeste + "teste0001");
        pdf.setAltura(800);
        pdf.setLargura(500);

        for (int i = 0; i <= 10; i++) {

            pdf.addConteudo("Linha número: " + i);

        }

        System.out.println("Testando classe GerarPdf metodo: gerar");
        assertEquals(true, pdf.gerar());

    }

}
