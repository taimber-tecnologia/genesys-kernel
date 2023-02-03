package br.com.salomaotech.sistema.jpa;

import br.com.salomaotech.sistema.algoritmos.ArquivoPropriedade;
import br.com.salomaotech.sistema.algoritmos.ValidaStringIsEmpty;
import java.util.Properties;

public class ConfiguracoesConexao {

    private final ArquivoPropriedade arquivoPropriedade;

    public ConfiguracoesConexao() {

        arquivoPropriedade = new ArquivoPropriedade(System.getProperty("user.dir") + "/target/config/rede.txt");

    }

    public Properties getPropriedades() {

        Properties propriedadesConexaoRetorno = null;

        try {

            /* configurações salvas */
            Properties propriedadesConexaoSalvas = arquivoPropriedade.getProperties();
            String servidor = propriedadesConexaoSalvas.getProperty("servidor");
            String banco = propriedadesConexaoSalvas.getProperty("banco");
            String login = propriedadesConexaoSalvas.getProperty("login");
            String senha = propriedadesConexaoSalvas.getProperty("senha");

            if (!ValidaStringIsEmpty.isEmpty(servidor) && !ValidaStringIsEmpty.isEmpty(banco) && !ValidaStringIsEmpty.isEmpty(login) && !ValidaStringIsEmpty.isEmpty(senha)) {

                /* popula as configurações */
                propriedadesConexaoRetorno = new Properties();
                propriedadesConexaoRetorno.put("javax.persistence.jdbc.url", "jdbc:mysql://" + servidor + ":3306" + "/" + banco);
                propriedadesConexaoRetorno.put("javax.persistence.jdbc.user", login);
                propriedadesConexaoRetorno.put("javax.persistence.jdbc.password", senha);

                /* configurações padrão */
                propriedadesConexaoRetorno.put("javax.persistence.jdbc.driver", "com.mysql.cj.jdbc.Driver");
                propriedadesConexaoRetorno.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
                propriedadesConexaoRetorno.put("hibernate.hbm2ddl.auto", "update");
                propriedadesConexaoRetorno.put("hibernate.show_sql", "false");
                propriedadesConexaoRetorno.put("hibernate.format_sql", "false");
                propriedadesConexaoRetorno.put("hibernate.connection.charSet", "UTF-8");

            }

        } catch (NullPointerException ex) {

        }

        return propriedadesConexaoRetorno;

    }

    public String getPathArquivo() {
        return arquivoPropriedade.absolutePath();
    }

}
