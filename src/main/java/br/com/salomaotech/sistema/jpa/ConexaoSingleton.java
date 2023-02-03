package br.com.salomaotech.sistema.jpa;

import static java.util.Objects.isNull;
import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class ConexaoSingleton {

    private static EntityManagerFactory entityManagerFactory;

    public void abrirConexao(String nomeConexao) {

        if (!isConexaoAberta()) {

            try {

                Properties propriedades = new ConfiguracoesConexao().getPropriedades();

                if (isNull(propriedades)) {

                    entityManagerFactory = Persistence.createEntityManagerFactory(nomeConexao);

                } else {

                    entityManagerFactory = Persistence.createEntityManagerFactory(nomeConexao, propriedades);

                }

            } catch (Exception ex) {

            }

        }

    }

    public void fecharConexao() {

        try {

            entityManagerFactory.close();
            entityManagerFactory = null;

        } catch (Exception ex) {

        }

    }

    public boolean isConexaoAberta() {

        try {

            return entityManagerFactory.isOpen();

        } catch (Exception ex) {

            return false;

        }

    }

    public EntityManager getEntityManager() {

        try {

            return entityManagerFactory.createEntityManager();

        } catch (Exception ex) {

            return null;

        }

    }

}
