package br.com.salomaotech.sistema.jpa;

import static java.util.Objects.isNull;
import org.junit.Test;
import static org.junit.Assert.*;

public class DaoTest {

    private ModeloDeTeste modeloDeTeste = new ModeloDeTeste();
    private final ConexaoSingleton conexaoSingleton = new ConexaoSingleton();
    private final Dao dao;

    public DaoTest() {

        conexaoSingleton.abrirConexao("Conexao");
        dao = new Dao(ModeloDeTeste.class, conexaoSingleton);

    }

    @Test
    public void testCreate() {

        /* cria o registro */
        dao.create(modeloDeTeste);

        System.out.println("Testando classe Dao metodo: create");
        assertEquals(true, modeloDeTeste.getId() != 0);

    }

    @Test
    public void testUpdate() {

        /* cria o registro */
        dao.create(modeloDeTeste);

        /* atualiza o registro e realiza pesquisa */
        modeloDeTeste.setNome("Teste");
        dao.update(modeloDeTeste);
        modeloDeTeste = (ModeloDeTeste) dao.findById(modeloDeTeste.getId());

        System.out.println("Testando classe Dao metodo: update");
        assertEquals(true, modeloDeTeste.getNome().equals(modeloDeTeste.getNome()));

    }

    @Test
    public void testDelete() {

        /* cria o registro e deleta-o em seguida */
        dao.create(modeloDeTeste);
        dao.delete(modeloDeTeste.getId());

        System.out.println("Testando classe Dao metodo: delete");
        assertEquals(true, isNull(dao.findById(modeloDeTeste.getId())));

    }

    @Test
    public void testFindById() {

        /* cria o registro */
        dao.create(modeloDeTeste);

        System.out.println("Testando classe Dao metodo: findById");
        assertEquals(false, isNull(dao.findById(modeloDeTeste.getId())));

    }

    @Test
    public void testFindBySqlQuery() {

        /* deleta todos os registros */
        new Repository(new ModeloDeTeste()).deleteTodos();

        /* simula o cadastro de registros */
        int i;
        for (i = 0; i <= 10; i++) {

            modeloDeTeste = new ModeloDeTeste();
            modeloDeTeste.setNome("Teste");
            dao.create(modeloDeTeste);

        }

        String sqlParametros = "SELECT objeto FROM " + ModeloDeTeste.class.getSimpleName() + " objeto WHERE objeto.nome='Teste'";

        System.out.println("Testando classe Dao metodo: findBySqlQuery etapa 01");
        assertEquals(true, dao.findBySqlQuery(sqlParametros, 0, 0).size() == i);

        System.out.println("Testando classe Dao metodo: findBySqlQuery etapa 02");
        assertEquals(true, dao.findBySqlQuery(sqlParametros, 0, 1).size() == 1);

        System.out.println("Testando classe Dao metodo: findBySqlQuery etapa 03");
        assertEquals(true, dao.findBySqlQuery(sqlParametros, 0, 2).size() == 2);

        System.out.println("Testando classe Dao metodo: findBySqlQuery etapa 04");
        assertEquals(true, dao.findBySqlQuery(sqlParametros, 0, 5).size() == 5);

        System.out.println("Testando classe Dao metodo: findBySqlQuery etapa 05");
        assertEquals(true, dao.findBySqlQuery(sqlParametros, 1, 5).size() == 5);

        System.out.println("Testando classe Dao metodo: findBySqlQuery etapa 06");
        assertEquals(true, dao.findBySqlQuery(sqlParametros, 2, 5).size() == 5);

        System.out.println("Testando classe Dao metodo: findBySqlQuery etapa 07");
        assertEquals(true, dao.findBySqlQuery(sqlParametros, 3, 5).size() == 1);

        System.out.println("Testando classe Dao metodo: findBySqlQuery etapa 08");
        assertEquals(true, dao.findBySqlQuery(sqlParametros, 4, 5).isEmpty());

        System.out.println("Testando classe Dao metodo: findBySqlQuery etapa 09");
        assertEquals(true, dao.findBySqlQuery(sqlParametros, 5, 5).isEmpty());

        System.out.println("Testando classe Dao metodo: findBySqlQuery etapa 10");
        assertEquals(true, dao.findBySqlQuery(sqlParametros, 6, 5).isEmpty());

    }

    @Test
    public void testFindTodos() {

        /* conta a quantidade de registros atual, e após isto adicione mais um */
        long quantidadeDeRegistrosAntigos = dao.findTodos().size();
        dao.create(modeloDeTeste);

        System.out.println("Testando classe Dao metodo: findTodos");
        assertEquals(true, dao.findTodos().size() > quantidadeDeRegistrosAntigos);

    }

    @Test
    public void testCountTodos() {

        /* deleta todos os registros */
        new Repository(new ModeloDeTeste()).deleteTodos();

        /* simula o cadastro de registros */
        int i;
        for (i = 0; i <= 10; i++) {

            modeloDeTeste = new ModeloDeTeste();
            modeloDeTeste.setNome("Teste " + i);
            dao.create(modeloDeTeste);

        }

        System.out.println("Testando classe dao metodo: countTodos etapa 01");
        assertEquals(true, dao.countTodos(null) == i);

        System.out.println("Testando classe dao metodo: countTodos etapa 02");
        assertEquals(true, dao.countTodos("WHERE objeto.nome='" + modeloDeTeste.getNome() + "'") == 1);

        System.out.println("Testando classe dao metodo: countTodos etapa 03");
        assertEquals(true, dao.countTodos("WHERE objeto.nome LIKE '%Teste%'") == i);

    }

}
