package br.com.salomaotech.sistema.jpa;

import br.com.salomaotech.sistema.algoritmos.Datas;
import br.com.salomaotech.sistema.patterns.Modelo;
import java.util.List;
import java.util.Map;
import static java.util.Objects.isNull;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class Repository {

    private final ConexaoSingleton conexaoSingleton;
    private final Modelo modelo;
    private final Dao dao;

    public Repository(Modelo modelo) {

        conexaoSingleton = new ConexaoSingleton();
        conexaoSingleton.abrirConexao("Conexao");

        /* propriedades */
        this.modelo = modelo;
        this.dao = new Dao(modelo.getClass(), conexaoSingleton);

    }

    /**
     * Salva no banco de dados
     *
     * @return long
     */
    public long save() {

        if (modelo.getId() == 0) {

            dao.create(modelo);

        } else {

            dao.update(modelo);

        }

        return modelo.getId();

    }

    /**
     * Pesquisa usando SQL
     *
     * @param queryParam Instrução SQL
     * @return List
     */
    public List getResults(String queryParam) {

        return dao.findBySqlQuery(queryParam, 0, 0);

    }

    /**
     * Pesquisa usando SQL com paginação
     *
     * @param queryParam Instrução SQL
     * @param pageNumber
     * @param pageSize
     * @return List
     */
    public List getResultsComPaginador(String queryParam, int pageNumber, int pageSize) {

        return dao.findBySqlQuery(queryParam, pageNumber, pageSize);

    }

    /**
     * Busca usando um ID de registro
     *
     * @param id ID do registro
     * @return Modelo
     */
    public Modelo findById(long id) {

        Modelo modeloPesquisa = (Modelo) dao.findById(id);

        if (isNull(modeloPesquisa)) {

            try {

                modeloPesquisa = (Modelo) Class.forName(modelo.getClass().getName()).newInstance();

            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex) {

            }

        }

        return modeloPesquisa;

    }

    /**
     * Deleta usando um ID de registro
     *
     * @param id ID de registro
     * @return boolean
     */
    public boolean delete(long id) {

        if (isNull(dao.findById(id))) {

            return false;

        } else {

            dao.delete(id);
            return isNull(dao.findById(id));

        }

    }

    /**
     * Deleta todos os registros da tabela
     */
    public void deleteTodos() {

        /* 
        OBS: Com este algoritmo é garantido que relacionamentos do tipo @OneToMany, @ManyToMany, @ManyToOne, @OneToOne serão excluídos
        Poderia ser utilizado DELETE FROM, más isto deixaria (cascade = CascadeType.ALL, orphanRemoval = true) inútil causando bug
         */
        List<Modelo> modeloList = dao.findTodos();

        modeloList.forEach(objeto -> {

            dao.delete(objeto.getId());

        });

    }

    /**
     * Retorna o número de registros
     *
     * @param condicaoSql Condição WHERE se houver
     * @return Número de registros com base na pesquisa
     */
    public long countTodos(String condicaoSql) {

        return dao.countTodos(condicaoSql);

    }

    /**
     * Formata o valor conforme seu tipo, adicionando ou removendo apóstrofos.
     * Se for uma data, converte para o formato 'Ano-Mês-Dia'.
     *
     * @param valor O valor a ser tratado.
     * @return O valor formatado.
     */
    private String tratarValorUpdate(Object valor) {

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

            default:

                /* valida se o valor é considerada é considerada uma data */
                if (Datas.isObjetoData(valor)) {

                    valor = Datas.calendarParaStringAnoMesDia(valor);

                }

        }

        return apostofro + valor + apostofro;

    }

    /**
     * Atualiza registros com os dados e as condições se houverem
     *
     * @param dados
     * @param jpql
     * @return
     */
    public int updateRegistros(Map<String, Object> dados, JPQL jpql) {

        // Se o JPQL for null então não atualiza nada
        if (isNull(jpql)) {
            return 0;
        }

        int linhasAfetadas = 0;

        try {

            EntityManager manager = conexaoSingleton.getEntityManager();
            EntityTransaction tx = manager.getTransaction();
            tx.begin();

            // Construir a parte SET da query (onde os dados serão atualizados)
            StringBuilder setClause = new StringBuilder();

            // Itera sobre o map de dados para montar a cláusula SET da SQL
            for (Map.Entry<String, Object> entry : dados.entrySet()) {

                // Se não for o primeiro parâmetro, adiciona uma vírgula para separar os campos
                if (setClause.length() > 0) {
                    setClause.append(", ");
                }

                // Se for null não há como fazer o tratamento do valor
                if (isNull(entry.getValue())) {

                    setClause.append(entry.getKey()).append(" = ").append(entry.getValue());

                } else {

                    String valor = tratarValorUpdate(entry.getValue());
                    setClause.append(entry.getKey()).append(" = ").append(valor);

                }

            }

            // SQL
            StringBuilder sql = new StringBuilder();
            sql.append("UPDATE ")
                    .append(modelo.getClass().getSimpleName())
                    .append(" AS ")
                    .append(jpql.getObjetoDadosDoSelect())
                    .append(" SET ")
                    .append(setClause)
                    .append(jpql.getCondicaoWhere());

            Query query = manager.createQuery(sql.toString());
            linhasAfetadas = query.executeUpdate();

            tx.commit();
            manager.close();

        } catch (Exception ex) {

        }

        return linhasAfetadas;

    }

}
