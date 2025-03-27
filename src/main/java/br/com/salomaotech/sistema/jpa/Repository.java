package br.com.salomaotech.sistema.jpa;

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
     * Atualiza registros com os dados e as condições se houverem
     *
     * @param dados
     * @param condicoes
     * @return
     */
    public int updateRegistros(Map<String, Object> dados, Map<String, RepositoryCondicaoWhere> condicoes) {

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

                setClause.append(entry.getKey()).append(" = ?");

            }

            // Construir a parte WHERE (se houver condições para restringir os registros)
            StringBuilder whereClause = new StringBuilder();

            if (condicoes != null && !condicoes.isEmpty()) {

                // Se existirem condições, inicia a cláusula WHERE
                whereClause.append(" WHERE ");

                // Itera sobre as condições para construir a cláusula WHERE
                for (Map.Entry<String, RepositoryCondicaoWhere> entry : condicoes.entrySet()) {

                    // Se não for a primeira condição, adiciona um "AND" para combinar as condições
                    if (whereClause.length() > 7) {
                        whereClause.append(" AND ");
                    }

                    // Obtém a condição do mapa
                    RepositoryCondicaoWhere condicao = entry.getValue();

                    // Caso contrário, utiliza o operador da condição e o valor fornecido
                    whereClause.append(entry.getKey()).append(" ").append(condicao.getOperador()).append(" ?");

                }

            }

            // Criar a query SQL nativa com as cláusulas SET e WHERE (se houver)
            String sql = "UPDATE " + modelo.getClass().getSimpleName() + " SET " + setClause + whereClause;
            Query query = manager.createNativeQuery(sql);

            // Definir os parâmetros SET para a query
            int index = 1;

            // Itera sobre os dados e define os parâmetros na query
            for (Map.Entry<String, Object> entry : dados.entrySet()) {

                query.setParameter(index++, entry.getValue());

            }

            // Definir os parâmetros WHERE (se houver)
            if (condicoes != null && !condicoes.isEmpty()) {

                // Itera sobre as condições e define os parâmetros na query
                for (Map.Entry<String, RepositoryCondicaoWhere> entry : condicoes.entrySet()) {

                    // Se o valor da condição não for null, define o parâmetro correspondente
                    if (entry.getValue().getValor() != null) {

                        query.setParameter(index++, entry.getValue().getValor());

                    }

                }

            }

            linhasAfetadas = query.executeUpdate();
            tx.commit();
            manager.close();

        } catch (Exception ex) {

        }

        return linhasAfetadas;

    }

}
