package br.com.salomaotech.sistema.jpa;

import org.junit.Test;
import static org.junit.Assert.*;

public class RepositoryTest {

    private ModeloDeTeste modeloDeTeste = new ModeloDeTeste();
    private Repository repository = new Repository(modeloDeTeste);

    @Test
    public void testSave() {

        System.out.println("Testando repository metodo: save");
        assertEquals(true, repository.save() != 0);

    }

    @Test
    public void testGetResults() {

        /* conta o número de registros antes de criar um novo, depois compara se a quantidade aumentou */
        int resultadosAntesDeSalvar = repository.getResults(new JPQL(modeloDeTeste).construirSelect()).size();
        repository.save();
        int resultadosDepoisDeSalvar = repository.getResults(new JPQL(modeloDeTeste).construirSelect()).size();

        System.out.println("Testando repository metodo: getResults");
        assertEquals(true, resultadosDepoisDeSalvar > resultadosAntesDeSalvar);

    }

    @Test
    public void testFindById() {

        /* cria o registro */
        repository.save();

        System.out.println("Testando repository metodo: findById");
        assertEquals(true, repository.findById(modeloDeTeste.getId()).getId() != 0);

    }

    @Test
    public void testDelete() {

        /* salva e depois deleta */
        repository.save();
        repository.delete(modeloDeTeste.getId());

        System.out.println("Testando repository metodo: delete");
        assertEquals(true, repository.findById(modeloDeTeste.getId()).getId() == 0);

    }

    @Test
    public void testDeleteTodos() {

        /* salva e depois deleta todos os registros */
        repository.save();
        repository.deleteTodos();
        int resultados = repository.getResults(new JPQL(modeloDeTeste).construirSelect()).size();

        System.out.println("Testando repository metodo: deleteTodos");
        assertEquals(true, resultados == 0);

    }

    @Test
    public void testCountTodos() {

        /* deleta todos os registros */
        new Repository(new ModeloDeTeste()).deleteTodos();

        /* simula cadastro */
        int i;
        for (i = 0; i <= 10; i++) {

            modeloDeTeste = new ModeloDeTeste();
            modeloDeTeste.setNome("Teste " + i);
            new Repository(modeloDeTeste).save();

        }

        /* esperado todos os registros */
        repository = new Repository(new ModeloDeTeste());
        System.out.println("Testando classe Repository metodo: countTodos etapa 01");
        assertEquals(true, repository.countTodos(null) == i);

        /* esperado todos os registros */
        JPQL jpql = new JPQL(new ModeloDeTeste());
        repository = new Repository(new ModeloDeTeste());
        System.out.println("Testando classe Repository metodo: countTodos etapa 02");
        assertEquals(true, repository.countTodos(jpql.getCondicaoWhere()) == i);

        /* esperado 0 registro */
        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroIgual("nome", "Teste " + (i + 1));
        repository = new Repository(new ModeloDeTeste());
        System.out.println("Testando classe Repository metodo: countTodos etapa 03");
        assertEquals(true, repository.countTodos(jpql.getCondicaoWhere()) == 0);

        /* esperado 1 registro */
        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroIgual("nome", "Teste " + (i - 1));
        repository = new Repository(new ModeloDeTeste());
        System.out.println("Testando classe Repository metodo: countTodos etapa 04");
        assertEquals(true, repository.countTodos(jpql.getCondicaoWhere()) == 1);

        /* esperado todos os registros */
        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroLike("nome", "Teste");
        repository = new Repository(new ModeloDeTeste());
        System.out.println("Testando classe Repository metodo: countTodos etapa 05");
        assertEquals(true, repository.countTodos(jpql.getCondicaoWhere()) == i);

    }

}
