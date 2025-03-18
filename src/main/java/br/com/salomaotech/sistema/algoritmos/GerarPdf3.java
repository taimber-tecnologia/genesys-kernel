package br.com.salomaotech.sistema.algoritmos;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GerarPdf3 {

    private final String pathDeSaidaDoArquivo;
    private final List<Paragraph> conteudoTexto = new ArrayList<>();
    private final List<PdfPTable> conteudoTabela = new ArrayList<>();
    private final Document document = new Document();

    public GerarPdf3(String pathDeSaidaDoArquivo) {
        this.pathDeSaidaDoArquivo = pathDeSaidaDoArquivo + ".pdf";
    }

    public String getPathDeSaidaDoArquivo() {
        return pathDeSaidaDoArquivo;
    }

    public void addConteudo(Paragraph paragrafo) {

        paragrafo.setAlignment(Element.ALIGN_CENTER);
        conteudoTexto.add(paragrafo);

    }

    public void addTabela(String[][] dados) {

        if (dados.length == 0 || dados[0].length == 0) {
            return;
        }

        PdfPTable tabela = new PdfPTable(dados[0].length);
        tabela.setHorizontalAlignment(Element.ALIGN_LEFT);

        for (String[] linha : dados) {

            for (String celula : linha) {
                tabela.addCell(celula);
            }

        }

        conteudoTabela.add(tabela);

    }

    public boolean gerar() {

        try {

            /* cria a pasta do arquivo caso não exista */
            CriaPastaLocal.criar(new File(this.pathDeSaidaDoArquivo).getParent());

            /* abre o documento */
            PdfWriter.getInstance(document, new FileOutputStream(pathDeSaidaDoArquivo));
            document.open();

            /* adiciona parágrafos */
            for (Paragraph paragrafo : conteudoTexto) {
                document.add(paragrafo);
            }

            /* adiciona tabelas */
            for (PdfPTable tabela : conteudoTabela) {
                document.add(tabela);
            }

            /* fecha o documento e verifica se foi gerado */
            document.close();
            return new File(pathDeSaidaDoArquivo).exists();

        } catch (DocumentException | IOException ex) {

            return false;

        }

    }

}
