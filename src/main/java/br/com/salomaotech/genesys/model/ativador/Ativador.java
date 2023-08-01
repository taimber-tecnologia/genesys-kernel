package br.com.salomaotech.genesys.model.ativador;

import br.com.salomaotech.sistema.algoritmos.HashMd5;
import br.com.salomaotech.sistema.algoritmos.CriaPastaLocal;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import static java.util.Objects.isNull;
import java.util.Properties;

public class Ativador {

    /* relacionado ao arquivo no disco */
    private String pathArquivo = System.getProperty("user.dir") + "/target/arquivos/lck/";
    private String nomeDoArquivo = HashMd5.cifrar("KEY_PROP_NOME_ARQUIVO");
    private String pathArquivoCompleto = pathArquivo + nomeDoArquivo + ".lck";

    /* propriedade de chaves de identificação de campos */
    private Properties propriedades = new Properties();
    private String chavePropAtivacao = HashMd5.cifrar("KEY_PROP_1");
    private String chavePropNumeroAberturas = HashMd5.cifrar("KEY_PROP_2");
    private String chavePropDataUltimaAbertura = HashMd5.cifrar("KEY_PROP_3");

    /* relacionado a ativação */
    private int numeroMaximoDeChaves = 10000;
    private String dataHoje;
    private int diasMaximoVersaoDemo = 4;
    private String preFixoDaChaveDeAtivacao = HashMd5.cifrar("+_PREFIX_CHAVE_ATIVACAO_+");
    private String posFixoDaChaveDeAtivacao = HashMd5.cifrar("*_POSFIX_CHAVE_ATIVACAO_*");
    private String ativadoTrue = HashMd5.cifrar("KEY_PROP_ATIVADO_TRUE");
    private String ativadoFalse = HashMd5.cifrar("KEY_PROP_ATIVADO_FALSE");

    /* relacionado ao nome do sistema */
    private String nomeSistema = "";

    public Ativador(String nomeSistema) {

        if (!isNull(nomeSistema)) {

            this.nomeSistema = HashMd5.cifrar(nomeSistema);

        }

        /* cria a pasta do arquivo se ela não existir */
        CriaPastaLocal.criar(pathArquivo);

        try {

            /* data de hoje */
            dataHoje = new SimpleDateFormat("yyyy/MM/dd").format(Calendar.getInstance().getTime());

            /* se o arquivo de ativação não existir, então cria-lo */
            if (!new File(pathArquivoCompleto).exists()) {

                new File(pathArquivoCompleto).createNewFile();

            }

            /* carrega as propriedades */
            propriedades.load(new FileInputStream(pathArquivoCompleto));

            /* não está ativado */
            if (isNull(propriedades.getProperty(chavePropAtivacao))) {

                propriedades.setProperty(chavePropAtivacao, HashMd5.cifrar(ativadoFalse));

            }

            /* número de aberturas */
            if (isNull(propriedades.getProperty(chavePropNumeroAberturas))) {

                propriedades.setProperty(chavePropNumeroAberturas, HashMd5.cifrar("1"));

            }

            /* data da última abertura */
            if (isNull(propriedades.getProperty(chavePropDataUltimaAbertura))) {

                propriedades.setProperty(chavePropDataUltimaAbertura, dataHoje);

            }

            /* se não estiver ativado então atualize o arquivo */
            if (!isAtivado()) {

                /* se a data do arquivo e data de hoje forem diferentes, incrementar mais uma abertura */
                if (!propriedades.get(chavePropDataUltimaAbertura).equals(dataHoje)) {

                    String incrementaDia = String.valueOf(getDiasUsados() + 1);
                    propriedades.setProperty(chavePropNumeroAberturas, HashMd5.cifrar(incrementaDia));

                }

                propriedades.setProperty(chavePropAtivacao, HashMd5.cifrar(ativadoFalse));
                propriedades.setProperty(chavePropDataUltimaAbertura, dataHoje);

                /* salva o arquivo */
                salvarArquivo();

            }

        } catch (IOException | NumberFormatException | NullPointerException ex) {

        }

    }

    /* ofuscador do arquivo */
    private void salvarArquivo() {

        /* novas propriedades */
        Properties novasPropriedades = new Properties();
        novasPropriedades.setProperty(chavePropAtivacao, propriedades.getProperty(chavePropAtivacao));
        novasPropriedades.setProperty(chavePropNumeroAberturas, propriedades.getProperty(chavePropNumeroAberturas));
        novasPropriedades.setProperty(chavePropDataUltimaAbertura, propriedades.getProperty(chavePropDataUltimaAbertura));

        for (int i = 0; i <= 10000; i++) {

            String dataAgora = Calendar.getInstance().getTime().toString();
            String chave = HashMd5.cifrar("CHK_" + dataAgora + i);
            String valor = HashMd5.cifrar("VAL_" + dataAgora + i);
            novasPropriedades.setProperty(chave, valor);

        }

        try {

            novasPropriedades.store(new FileOutputStream(pathArquivoCompleto), null);

        } catch (IOException ex) {

        }

    }

    public final int getDiasUsados() {

        int i = 1;

        while (!propriedades.getProperty(chavePropNumeroAberturas).equals(HashMd5.cifrar(String.valueOf(i)))) {

            i++;

        }

        return i;

    }

    public int getDiasRestantes() {

        int diasRestantes = diasMaximoVersaoDemo - getDiasUsados();

        /* se os dias restantes forem negativos então vale zero */
        if (diasRestantes <= 0) {

            diasRestantes = 0;

        }

        return diasRestantes;

    }

    public boolean isExpirou() {

        if (isAtivado()) {

            return false;

        } else {

            return getDiasRestantes() <= 0;

        }

    }

    public final boolean isAtivado() {

        return propriedades.getProperty(chavePropAtivacao).equals(HashMd5.cifrar(ativadoTrue));

    }

    public ArrayList getChaves() {

        ArrayList retorno = new ArrayList();

        /* gera as chaves */
        for (int i = 0; i <= numeroMaximoDeChaves; i++) {

            retorno.add(HashMd5.cifrar(preFixoDaChaveDeAtivacao + i + nomeSistema + posFixoDaChaveDeAtivacao));

        }

        return retorno;

    }

    public boolean ativar(String chave) {

        /* se a chave informada estiver na lista então pode ativar */
        if (getChaves().contains(chave)) {

            propriedades.setProperty(chavePropAtivacao, HashMd5.cifrar(ativadoTrue));
            salvarArquivo();
            return true;

        } else {

            return false;

        }

    }

    public String getPathArquivoCompleto() {
        return pathArquivoCompleto;
    }

}
