package br.com.salomaotech.sistema.jpa;

import br.com.salomaotech.sistema.modelos.ModeloDeTeste;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import org.junit.Test;
import static org.junit.Assert.*;

public class RepositoryTest {

    private ModeloDeTeste modeloDeTeste = new ModeloDeTeste();
    private Repository repository = new Repository(modeloDeTeste);

    private void simularCadastro() {

        // Limpa a tabela antes do teste
        repository.deleteTodos();

        Calendar nascimento = Calendar.getInstance();
        nascimento.set(Calendar.YEAR, 1989);
        nascimento.set(Calendar.MONTH, Calendar.SEPTEMBER);
        nascimento.set(Calendar.DAY_OF_MONTH, 15);

        // Cria 5 registros de teste
        for (int i = 1; i <= 5; i++) {

            ModeloDeTeste modelo = new ModeloDeTeste();
            modelo.setNome("Teste Nome " + i);
            modelo.setIdade(30 + i);
            modelo.setNascimento(nascimento);
            modelo.setSegundosDeVida(1000L + i);
            new Repository(modelo).save();

        }

    }

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

        JPQL jpql;
        Map<String, Object> dados;

        // Cenário 1 - Se o JPQL for null então não atualiza nada
        simularCadastro();
        jpql = null;
        dados = new HashMap<>();
        dados.put("nome", "Novo Nome 1");
        dados.put("idade", 20);
        assertEquals(0, repository.updateRegistros(dados, jpql));

        // Cenário 2 - Atualizar registros com nome igual
        simularCadastro();
        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroIgual("nome", "Teste Nome 1");
        dados = new HashMap<>();
        dados.put("nome", "Novo Nome 1");
        assertEquals(1, repository.updateRegistros(dados, jpql));

        // Cenário 3 - Atualizar registros com idade diferente
        simularCadastro();
        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroDiferente("idade", 32);
        dados = new HashMap<>();
        dados.put("nome", "Nome Diferente");
        assertEquals(4, repository.updateRegistros(dados, jpql));

        // Cenário 4 - Atualizar registros com nome contendo parte do valor
        simularCadastro();
        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroLike("nome", "Teste Nome");
        dados = new HashMap<>();
        dados.put("nome", "Nome Like Atualizado");
        assertEquals(5, repository.updateRegistros(dados, jpql));

        // Cenário 5 - Atualizar registros com nascimento não nulo
        simularCadastro();
        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroNaoNulo("nascimento");
        dados = new HashMap<>();
        dados.put("nome", "Nome Não Nulo");
        assertEquals(5, repository.updateRegistros(dados, jpql));

        // Cenário 6 - Atualizar registros com nascimento nulo
        simularCadastro();
        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroNulo("nascimento");
        dados = new HashMap<>();
        dados.put("nome", "Nome Nulo");
        assertEquals(0, repository.updateRegistros(dados, jpql));

        // Cenário 7 - Atualizar registros com idade maior ou igual
        simularCadastro();
        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroMaiorIgual("idade", 33);
        dados = new HashMap<>();
        dados.put("idade", 99);
        assertEquals(3, repository.updateRegistros(dados, jpql));

        // Cenário 8 - Atualizar registros com idade menor ou igual
        simularCadastro();
        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroMenorIgual("idade", 32);
        dados = new HashMap<>();
        dados.put("idade", 10);
        assertEquals(2, repository.updateRegistros(dados, jpql));

        // Cenário 9 - Atualizar registros com idade menor
        simularCadastro();
        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroMenor("idade", 34);
        dados = new HashMap<>();
        dados.put("idade", 20);
        assertEquals(3, repository.updateRegistros(dados, jpql));

        // Cenário 10 - Atualizar registros com relação entre segundos de vida e idade
        simularCadastro();
        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroCompararDuasChaves("segundosDeVida", "idade", ">");
        dados = new HashMap<>();
        dados.put("idade", 50);
        assertEquals(5, repository.updateRegistros(dados, jpql));

        // Cenário 11 - Atualizar registros que atendem a múltiplas condições
        simularCadastro();
        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroMaiorIgual("idade", 33);
        jpql.addParametroNaoNulo("nascimento");
        dados = new HashMap<>();
        dados.put("nome", "Nome Condicional");
        assertEquals(3, repository.updateRegistros(dados, jpql));

        // Cenário 12 - Atualizar registros com ordem específica (DESC)
        simularCadastro();
        jpql = new JPQL(new ModeloDeTeste());
        jpql.addOrderBy("idade", "DESC");
        dados = new HashMap<>();
        dados.put("nome", "Nome Ordenado");
        assertEquals(5, repository.updateRegistros(dados, jpql));

        // Cenário 13 - Atualizar registros com nome diferente de um valor específico
        simularCadastro();
        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroDiferente("nome", "Teste Nome 3");
        dados = new HashMap<>();
        dados.put("nome", "Nome Diferente Condicional");
        assertEquals(4, repository.updateRegistros(dados, jpql));

        // Cenário 14 - Atualizar registros com idade igual e comparação entre chaves
        simularCadastro();
        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroIgual("idade", 34);
        jpql.addParametroCompararDuasChaves("segundosDeVida", "idade", ">");
        dados = new HashMap<>();
        dados.put("nome", "Nome Específico e Comparado");
        assertEquals(1, repository.updateRegistros(dados, jpql));

        // Cenário 15 - Atualizar registros com nome contendo substring em letras minúsculas
        simularCadastro();
        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroLike("nome", "nome");
        dados = new HashMap<>();
        dados.put("nome", "Nome Com Substring");
        assertEquals(5, repository.updateRegistros(dados, jpql));

        // Cenário 16 - Atualizar registros usando NOT NULL e condições combinadas
        simularCadastro();
        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroNaoNulo("segundosDeVida");
        jpql.addParametroMaiorIgual("idade", 31);
        dados = new HashMap<>();
        dados.put("nome", "Nome Condições Combinadas");
        assertEquals(5, repository.updateRegistros(dados, jpql));

        // Cenário 17 - Atualizar registros com menos de dois valores combinados
        simularCadastro();
        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroMenor("segundosDeVida", 1003);
        dados = new HashMap<>();
        dados.put("nome", "Nome Menos Valores");
        assertEquals(2, repository.updateRegistros(dados, jpql));

    }

}
