package br.com.salomaotech.sistema.algoritmos;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GerarPdf2 {

    private final String pathDeSaidaDoArquivo;
    private final List<Paragraph> conteudoDoCorpoDoDocumento = new ArrayList();
    private final Document document = new Document();

    public GerarPdf2(String pathDeSaidaDoArquivo) {

        this.pathDeSaidaDoArquivo = pathDeSaidaDoArquivo + ".pdf";

    }

    public String getPathDeSaidaDoArquivo() {

        return pathDeSaidaDoArquivo;

    }

    public void addConteudo(Paragraph paragrafo) {

        conteudoDoCorpoDoDocumento.add(paragrafo);

    }

    public boolean gerar() {

        try {

            /* cria a pasta do arquivo caso não exista */
            CriaPastaLocal.criar(new File(this.pathDeSaidaDoArquivo).getParent());

            /* abre o documento */
            PdfWriter.getInstance(document, new FileOutputStream(pathDeSaidaDoArquivo));
            document.open();

            /* adiciona as linhas de conteúdo do corpo do documento */
            conteudoDoCorpoDoDocumento.forEach(paragrafo -> {

                try {

                    document.add(paragrafo);

                } catch (DocumentException | java.lang.NullPointerException ex) {

                }

            });

            /* fecha o documento e abre o arquivo */
            document.close();
            return new File(pathDeSaidaDoArquivo).exists();

        } catch (DocumentException | IOException | java.lang.NullPointerException ex) {

            return false;

        }

    }

}
