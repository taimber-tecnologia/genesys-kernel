package br.com.salomaotech.sistema.jpa;

import java.util.ArrayList;
import java.util.List;
import static java.util.Objects.isNull;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class Dao<E> {

    private final Class nomeTabela;
    private final ConexaoSingleton conexaoSingleton;
    private final String objetoDados = "objeto";

    public Dao(Class nomeTabela, ConexaoSingleton conexaoSingleton) {
        this.nomeTabela = nomeTabela;
        this.conexaoSingleton = conexaoSingleton;
    }

    /**
     * Criar um registro no banco de dados
     *
     * @param entidade @Entity
     */
    public void create(E entidade) {

        try {

            EntityManager entityManager = conexaoSingleton.getEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.persist(entidade);
            entityTransaction.commit();
            entityManager.refresh(entidade);
            entityManager.close();

        } catch (Exception ex) {

        }

    }

    /**
     * Atualizar um registro no banco de dados
     *
     * @param entidade @Entity
     */
    public void update(E entidade) {

        try {

            EntityManager entityManager = conexaoSingleton.getEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.merge(entidade);
            entityTransaction.commit();
            entityManager.refresh(entidade);
            entityManager.close();

        } catch (Exception ex) {

        }

    }

    /**
     * Deletar um registro do banco de dados por ID
     *
     * @param id ID do registro
     */
    public void delete(Long id) {

        try {

            EntityManager entityManager = conexaoSingleton.getEntityManager();
            EntityTransaction entityTransaction = entityManager.getTransaction();
            entityTransaction.begin();
            entityManager.remove(entityManager.find(nomeTabela, id));
            entityTransaction.commit();
            entityManager.close();

        } catch (Exception ex) {

        }

    }

    /**
     * Buscar um registro do banco de dados por ID
     *
     * @param id ID do registro
     * @return @Entity
     */
    public E findById(Long id) {

        E entidadeRetorno = null;

        try {

            EntityManager entityManager = conexaoSingleton.getEntityManager();
            entidadeRetorno = (E) entityManager.find(nomeTabela, id);
            entityManager.close();

        } catch (Exception ex) {

        }

        return entidadeRetorno;

    }

    /**
     * Busca por registros no banco de dados utilizando SQL
     *
     * @param sqlParametros Parâmetros SQL
     * @param pageNumber
     * @param pageSize
     *
     * @return List com @Entity
     */
    public List findBySqlQuery(String sqlParametros, int pageNumber, int pageSize) {

        List EntidadesRetorno = new ArrayList();

        try {

            EntityManager entityManager = conexaoSingleton.getEntityManager();
            TypedQuery query = entityManager.createQuery(sqlParametros, nomeTabela);

            /* avança no contador dos registros */
            if (pageNumber > 0) {

                query.setFirstResult((pageNumber - 1) * pageSize);

            }

            /* define número máximo de registros */
            if (pageSize > 0) {

                query.setMaxResults(pageSize);

            }

            EntidadesRetorno = query.getResultList();
            entityManager.close();

        } catch (Exception ex) {

        }

        return EntidadesRetorno;

    }

    /**
     * Lista todos os registros do banco de dados
     *
     * @return @Entity
     */
    public List findTodos() {

        return findBySqlQuery("SELECT " + objetoDados + " FROM " + nomeTabela.getSimpleName() + " " + objetoDados, 0, 0);

    }

    /**
     * Retorna o número de registros
     *
     * @param condicaoSql Condição WHERE se houver
     * @return Número de registros com base na pesquisa
     */
    public long countTodos(String condicaoSql) {

        long resultados = 0;

        try {

            String sqlParametros = "SELECT COUNT(" + objetoDados + ".id) FROM " + nomeTabela.getSimpleName() + " " + objetoDados;

            if (!isNull(condicaoSql)) {

                sqlParametros += " " + condicaoSql;

            }

            EntityManager entityManager = conexaoSingleton.getEntityManager();
            resultados = (long) entityManager.createQuery(sqlParametros, Long.class).getSingleResult();
            entityManager.close();

        } catch (Exception ex) {

        }

        return resultados;

    }

}
