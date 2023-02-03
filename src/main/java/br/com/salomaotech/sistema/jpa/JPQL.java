package br.com.salomaotech.sistema.jpa;

import br.com.salomaotech.sistema.algoritmos.ValidaStringIsEmpty;
import br.com.salomaotech.sistema.patterns.Modelo;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static java.util.Objects.isNull;

public class JPQL {

    private final String nomeTabela;
    private final String objetoDadosDoSelect;
    private final List colunasPesquisar;
    private final List colunasOrdenar;

    public JPQL(Modelo modelo) {

        nomeTabela = modelo.getClass().getSimpleName();
        objetoDadosDoSelect = "objeto";
        colunasPesquisar = new ArrayList();
        colunasOrdenar = new ArrayList();

    }

    private String geraItemColunaPesquisa(String chave, Object valor, String clausula) {

        String apostofro = "'";

        switch (valor.getClass().getName()) {

            case "java.lang.Integer":
                apostofro = "";
                break;

            case "java.lang.Boolean":
                apostofro = "";
                break;

            case "java.lang.Long":
                apostofro = "";
                break;

            case "java.util.Date":
                Date data = (Date) valor;
                valor = new Timestamp(data.getTime());
                break;

        }

        return objetoDadosDoSelect + "." + chave + clausula + apostofro + valor + apostofro;

    }

    private String constroiParametro(List tipoParametros, int tipoAcao) {

        String retorno = "";
        int contador = 0;

        for (Object parametro : tipoParametros) {

            if (contador > 0) {

                switch (tipoAcao) {

                    case 0:
                        parametro = " AND " + parametro;
                        break;

                    case 1:
                        parametro = ", " + parametro;
                        break;

                }

            }

            retorno += parametro;
            contador++;

        }

        return retorno;

    }

    public void addParametroIgual(String chave, Object valor) {

        if (!ValidaStringIsEmpty.isEmpty(chave) & !isNull(valor)) {

            colunasPesquisar.add(geraItemColunaPesquisa(chave, valor, "="));

        }

    }

    public void addParametroDiferente(String chave, Object valor) {

        if (!ValidaStringIsEmpty.isEmpty(chave) & !isNull(valor)) {

            colunasPesquisar.add(geraItemColunaPesquisa(chave, valor, "!="));

        }

    }

    public void addParametroLike(String chave, Object valor) {

        if (!ValidaStringIsEmpty.isEmpty(chave) & !isNull(valor)) {

            colunasPesquisar.add(objetoDadosDoSelect + "." + chave + " LIKE '%" + valor + "%'");

        }

    }

    public void addParametroNaoNulo(String chave) {

        if (!ValidaStringIsEmpty.isEmpty(chave)) {

            colunasPesquisar.add(objetoDadosDoSelect + "." + chave + "!=null");

        }

    }

    public void addParametroNulo(String chave) {

        if (!ValidaStringIsEmpty.isEmpty(chave)) {

            colunasPesquisar.add(objetoDadosDoSelect + "." + chave + "=null");

        }

    }

    public void addParametroMaiorIgual(String chave, Object valor) {

        if (!ValidaStringIsEmpty.isEmpty(chave) & !isNull(valor)) {

            colunasPesquisar.add(geraItemColunaPesquisa(chave, valor, ">="));

        }

    }

    public void addParametroMenorIgual(String chave, Object valor) {

        if (!ValidaStringIsEmpty.isEmpty(chave) & !isNull(valor)) {

            colunasPesquisar.add(geraItemColunaPesquisa(chave, valor, "<="));

        }

    }

    public void addParametroMenor(String chave, Object valor) {

        if (!ValidaStringIsEmpty.isEmpty(chave) & !isNull(valor)) {

            colunasPesquisar.add(geraItemColunaPesquisa(chave, valor, "<"));

        }

    }

    public void addParametroCompararDuasChaves(String chaveA, String chaveB, String clausula) {

        if (!ValidaStringIsEmpty.isEmpty(chaveA) & !ValidaStringIsEmpty.isEmpty(chaveB) & !ValidaStringIsEmpty.isEmpty(clausula)) {

            colunasPesquisar.add(objetoDadosDoSelect + "." + chaveA + " " + clausula + " " + objetoDadosDoSelect + "." + chaveB);

        }

    }

    public void addOrderBy(String chave, Object valor) {

        if (!ValidaStringIsEmpty.isEmpty(chave) & !isNull(valor)) {

            colunasOrdenar.add(objetoDadosDoSelect + "." + chave + " " + valor);

        }

    }

    public String getCondicaoWhere() {

        String condicaoWhere = "";

        if (!colunasPesquisar.isEmpty()) {

            condicaoWhere += " WHERE " + constroiParametro(colunasPesquisar, 0);

        }

        return condicaoWhere;

    }

    public String construirSelect() {

        String sqlParametros = "SELECT " + objetoDadosDoSelect + " FROM " + nomeTabela + " " + objetoDadosDoSelect + getCondicaoWhere();

        if (!colunasOrdenar.isEmpty()) {

            sqlParametros += " ORDER BY " + constroiParametro(colunasOrdenar, 1);

        }

        return sqlParametros;

    }

}
