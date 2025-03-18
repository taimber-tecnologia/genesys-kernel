package br.com.salomaotech.sistema.algoritmos;

import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import org.junit.Test;
import static org.junit.Assert.*;

public class GerarPdf3Test {

    private final String pathLocal = System.getProperty("user.dir") + "/target/arquivos/testes_remover/";

    @Test
    public void testGerarPdfComTextoETabela() {

        String pastaDeTeste = pathLocal + "gera_pdf/";
        GerarPdf3 pdf = new GerarPdf3(pastaDeTeste + "teste0002");

        // Adiciona parágrafos ao PDF
        for (int i = 0; i <= 10; i++) {

            pdf.addConteudo(new Paragraph("Linha número " + i, new Font(Font.FontFamily.HELVETICA, 11, Font.BOLD)));

        }

        // Adiciona uma tabela ao PDF
        String[][] dados = {
            {"Coluna 1", "Coluna 2", "Coluna 3"},
            {"1", "2", "3"},
            {"4", "5", "6"},
            {"7", "8", "9"}
        };

        pdf.addTabela(dados);

        System.out.println("Testando classe GerarPdf3 metodo: gerar");
        assertEquals(true, pdf.gerar());

    }

}
