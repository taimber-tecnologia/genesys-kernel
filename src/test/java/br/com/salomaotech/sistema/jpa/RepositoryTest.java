package br.com.salomaotech.sistema.jpa;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    @Test
    public void testUpdateRegistros() {

        // Limpa a tabela antes do teste
        repository.deleteTodos();

        // Cria 5 registros de teste
        for (int i = 1; i <= 5; i++) {

            ModeloDeTeste modelo = new ModeloDeTeste();
            modelo.setNome("Teste " + i);
            modelo.setIdade(20 + i);
            new Repository(modelo).save();

        }

        // Teste 1: Atualização sem WHERE (deve atualizar todos os registros)
        Map<String, Object> dadosUpdate = new HashMap<>();
        dadosUpdate.put("idade", 30);

        int linhasAfetadas = repository.updateRegistros(dadosUpdate, null);
        System.out.println("Testando repository metodo: updateRegistros (sem WHERE)");
        assertEquals(5, linhasAfetadas);

        // Verifica se todos foram atualizados
        List<ModeloDeTeste> resultados = repository.getResults(new JPQL(modeloDeTeste).construirSelect());
        for (ModeloDeTeste modelo : resultados) {

            assertEquals(30, modelo.getIdade());

        }

        // Teste 2: Atualização com WHERE simples
        Map<String, Object> condicao = new HashMap<>();
        condicao.put("nome", "Teste 3");

        dadosUpdate.clear();
        dadosUpdate.put("idade", 33);
        dadosUpdate.put("segundosDeVida", 1000L);

        linhasAfetadas = repository.updateRegistros(dadosUpdate, condicao);
        System.out.println("Testando repository metodo: updateRegistros (com WHERE simples)");
        assertEquals(1, linhasAfetadas);

        // Verifica se apenas o registro específico foi atualizado
        ModeloDeTeste modeloAtualizado = (ModeloDeTeste) repository.getResults("SELECT m FROM ModeloDeTeste m WHERE m.nome = 'Teste 3'").get(0);
        assertEquals(33, modeloAtualizado.getIdade());
        assertEquals(1000L, modeloAtualizado.getSegundosDeVida());

        // Teste 3: Atualização com múltiplas condições
        Map<String, Object> condicaoMultipla = new HashMap<>();
        condicaoMultipla.put("idade", 30);
        condicaoMultipla.put("segundosDeVida", 0L);

        dadosUpdate.clear();
        dadosUpdate.put("nome", "Atualizado");

        linhasAfetadas = repository.updateRegistros(dadosUpdate, condicaoMultipla);
        System.out.println("Testando repository metodo: updateRegistros (com WHERE múltiplo)");
        assertEquals(4, linhasAfetadas); // 4 registros tem idade=30 e segundosDeVida=0

        // Verifica se os registros corretos foram atualizados
        resultados = repository.getResults("SELECT m FROM ModeloDeTeste m WHERE m.nome = 'Atualizado'");
        assertEquals(4, resultados.size());

    }

}
