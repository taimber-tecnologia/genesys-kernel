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
     * @param dados Dados
     * @param condicao Condição se houver
     * @return Retorna o número de linhas afetadas
     */
    public int updateRegistros(Map<String, Object> dados, Map<String, Object> condicao) {

        int linhasAfetadas = 0;

        try {

            EntityManager manager = conexaoSingleton.getEntityManager();
            EntityTransaction tx = manager.getTransaction();
            tx.begin();

            // Construir a parte SET da query
            StringBuilder setClause = new StringBuilder();

            for (Map.Entry<String, Object> entry : dados.entrySet()) {

                if (setClause.length() > 0) {
                    setClause.append(", ");
                }

                setClause.append(entry.getKey()).append(" = ?");

            }

            // Construir a parte WHERE (se houver condições)
            StringBuilder whereClause = new StringBuilder();

            if (condicao != null && !condicao.isEmpty()) {

                whereClause.append(" WHERE ");

                for (Map.Entry<String, Object> entry : condicao.entrySet()) {

                    if (whereClause.length() > 7) {
                        whereClause.append(" AND ");
                    }

                    whereClause.append(entry.getKey()).append(" = ?");

                }

            }

            // Criar a query SQL nativa
            String sql = "UPDATE " + modelo.getClass().getSimpleName() + " SET " + setClause + whereClause;
            Query query = manager.createNativeQuery(sql);

            // Definir os parâmetros SET
            int index = 1;

            for (Map.Entry<String, Object> entry : dados.entrySet()) {

                query.setParameter(index++, entry.getValue());

            }

            // Definir os parâmetros WHERE (se houver)
            if (condicao != null && !condicao.isEmpty()) {

                for (Map.Entry<String, Object> entry : condicao.entrySet()) {

                    query.setParameter(index++, entry.getValue());

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
