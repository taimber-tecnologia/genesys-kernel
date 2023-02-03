package br.com.salomaotech.sistema.algoritmos;

import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import org.junit.Test;
import static org.junit.Assert.*;

public class GerarPdf2Test {

    private final String pathLocal = System.getProperty("user.dir") + "/target/arquivos/testes_remover/";

    @Test
    public void testGerarPdf() {

        String pastaDeTeste = pathLocal + "gera_pdf/";
        GerarPdf2 pdf = new GerarPdf2(pastaDeTeste + "teste0001");

        for (int i = 0; i <= 30; i++) {

            pdf.addConteudo(new Paragraph("Linha número " + i, new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD)));

        }

        System.out.println("Testando classe GerarPdf metodo: gerar");
        assertEquals(true, pdf.gerar());

    }

}
