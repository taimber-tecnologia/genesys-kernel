package br.com.salomaotech.sistema.algoritmos;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import static java.util.Objects.isNull;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

public class CopiaArquivosComDialogo {

    private final JFileChooser jFileChooser;
    private FileNameExtensionFilter filtro;
    private final String pathDestino;
    private final boolean isSelecionarMultiplos;
    private final List listaDeArquivosCopiados = new ArrayList();

    public CopiaArquivosComDialogo(String pathDestino, boolean isSelecionarMultiplos) {

        this.pathDestino = pathDestino;
        CriaPastaLocal.criar(pathDestino);

        this.isSelecionarMultiplos = isSelecionarMultiplos;
        jFileChooser = new JFileChooser();
        jFileChooser.setMultiSelectionEnabled(isSelecionarMultiplos);

    }

    public void setFiltro(FileNameExtensionFilter filtro) {

        this.filtro = filtro;

    }

    public boolean copiar() {

        int numeroDeFalhas = 0;

        /* valida se irá usar filtro de arquivos */
        if (!isNull(filtro)) {

            jFileChooser.addChoosableFileFilter(filtro);
            jFileChooser.setFileFilter(filtro);

        }

        /* valida se o diálogo foi aprovado pelo usuário */
        if (jFileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION & !ValidaStringIsEmpty.isEmpty(pathDestino)) {

            /* valida se está trabalhando com múltiplos arquivos */
            if (isSelecionarMultiplos) {

                /* pega os arquivos selecionados no diálogo */
                for (File arquivo : jFileChooser.getSelectedFiles()) {

                    try {

                        /* copia da origem para o destino */
                        String destinoFinalDoArquivo = pathDestino + "/" + arquivo.getName();
                        Files.copy(arquivo.toPath(), new File(destinoFinalDoArquivo).toPath(), StandardCopyOption.REPLACE_EXISTING);
                        listaDeArquivosCopiados.add(destinoFinalDoArquivo);

                    } catch (IOException ex) {

                        numeroDeFalhas++;

                    }

                }

            } else {

                try {

                    /* copia o único arquivo da origem para o destino */
                    File arquivo = jFileChooser.getSelectedFile();
                    String destinoFinalDoArquivo = pathDestino + "/" + arquivo.getName();
                    Files.copy(arquivo.toPath(), new File(destinoFinalDoArquivo).toPath(), StandardCopyOption.REPLACE_EXISTING);
                    listaDeArquivosCopiados.add(destinoFinalDoArquivo);

                } catch (IOException ex) {

                    numeroDeFalhas++;

                }

            }

        } else {

            numeroDeFalhas++;

        }

        /* retorna se não houveram falhas */
        return numeroDeFalhas == 0;

    }

    public List<String> getListaDeArquivosCopiados() {
        return listaDeArquivosCopiados;
    }

}
