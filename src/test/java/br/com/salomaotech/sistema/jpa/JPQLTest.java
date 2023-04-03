package br.com.salomaotech.sistema.jpa;

import java.util.Calendar;
import static java.util.Objects.isNull;
import org.junit.Test;
import static org.junit.Assert.*;

public class JPQLTest {

    private JPQL jpql;
    private final String chaveNome = "nome";
    private final String chaveIdade = "idade";
    private final String chaveNascimento = "nascimento";
    private final String chaveSegundosDeVida = "segundosDeVida";
    private final Calendar nascimento = Calendar.getInstance();

    public JPQLTest() {

        nascimento.set(Calendar.YEAR, 1989);
        nascimento.set(Calendar.MONTH, Calendar.SEPTEMBER);
        nascimento.set(Calendar.DAY_OF_MONTH, 15);

    }

    @Test
    public void testAddParametroIgual() {

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroIgual(chaveNome, "Teste");
        System.out.println("Testando classe JPQL metodo: addParametroIgual etapa 01");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.nome='Teste'"));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroIgual(chaveIdade, "18");
        System.out.println("Testando classe JPQL metodo: addParametroIgual etapa 02");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.idade='18'"));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroIgual(chaveIdade, 18);
        System.out.println("Testando classe JPQL metodo: addParametroIgual etapa 03");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.idade=18"));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroIgual(chaveNascimento, nascimento);
        System.out.println("Testando classe JPQL metodo: addParametroIgual etapa 04");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.nascimento='1989-09-15'"));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroIgual(chaveSegundosDeVida, 9223372036854775807L);
        System.out.println("Testando classe JPQL metodo: addParametroIgual etapa 05");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.segundosDeVida=9223372036854775807"));

    }

    @Test
    public void testAddParametroDiferente() {

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroDiferente(chaveNome, "Teste");
        System.out.println("Testando classe JPQL metodo: addParametroDiferente etapa 01");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.nome!='Teste'"));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroDiferente(chaveIdade, "18");
        System.out.println("Testando classe JPQL metodo: addParametroDiferente etapa 02");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.idade!='18'"));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroDiferente(chaveIdade, 18);
        System.out.println("Testando classe JPQL metodo: addParametroDiferente etapa 03");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.idade!=18"));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroDiferente(chaveNascimento, nascimento);
        System.out.println("Testando classe JPQL metodo: addParametroDiferente etapa 04");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.nascimento!='1989-09-15'"));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroDiferente(chaveSegundosDeVida, 9223372036854775807L);
        System.out.println("Testando classe JPQL metodo: addParametroDiferente etapa 05");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.segundosDeVida!=9223372036854775807"));

    }

    @Test
    public void testAddParametroLike() {

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroLike(chaveNome, "Teste");
        System.out.println("Testando classe JPQL metodo: addParametroLike etapa 01");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE LOWER(objeto.nome) LIKE LOWER('%Teste%')"));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroLike(chaveIdade, "18");
        System.out.println("Testando classe JPQL metodo: addParametroLike etapa 02");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE LOWER(objeto.idade) LIKE LOWER('%18%')"));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroLike(chaveIdade, 18);
        System.out.println("Testando classe JPQL metodo: addParametroLike etapa 03");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE LOWER(objeto.idade) LIKE LOWER('%18%')"));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroLike(chaveNascimento, nascimento);
        System.out.println("Testando classe JPQL metodo: addParametroLike etapa 04");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE LOWER(objeto.nascimento) LIKE LOWER('%1989-09-15%')"));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroLike(chaveSegundosDeVida, 9223372036854775807L);
        System.out.println("Testando classe JPQL metodo: addParametroLike etapa 05");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE LOWER(objeto.segundosDeVida) LIKE LOWER('%9223372036854775807%')"));

    }

    @Test
    public void testAddParametroNaoNulo() {

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroNaoNulo(chaveNome);
        System.out.println("Testando classe JPQL metodo: addParametroNaoNulo etapa 01");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.nome!=null"));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroNaoNulo(chaveIdade);
        System.out.println("Testando classe JPQL metodo: addParametroNaoNulo etapa 02");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.idade!=null"));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroNaoNulo(chaveNascimento);
        System.out.println("Testando classe JPQL metodo: addParametroNaoNulo etapa 03");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.nascimento!=null"));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroNaoNulo(chaveSegundosDeVida);
        System.out.println("Testando classe JPQL metodo: addParametroNaoNulo etapa 04");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.segundosDeVida!=null"));

    }

    @Test
    public void testAddParametroNulo() {

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroNulo(chaveNome);
        System.out.println("Testando classe JPQL metodo: addParametroNulo etapa 01");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.nome=null"));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroNulo(chaveIdade);
        System.out.println("Testando classe JPQL metodo: addParametroNulo etapa 02");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.idade=null"));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroNulo(chaveNascimento);
        System.out.println("Testando classe JPQL metodo: addParametroNulo etapa 03");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.nascimento=null"));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroNulo(chaveSegundosDeVida);
        System.out.println("Testando classe JPQL metodo: addParametroNulo etapa 04");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.segundosDeVida=null"));

    }

    @Test
    public void testAddParametroMaiorIgual() {

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroMaiorIgual(chaveNome, "Teste");
        System.out.println("Testando classe JPQL metodo: addParametroMaiorIgual etapa 01");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.nome>='Teste'"));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroMaiorIgual(chaveIdade, "18");
        System.out.println("Testando classe JPQL metodo: addParametroMaiorIgual etapa 02");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.idade>='18'"));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroMaiorIgual(chaveIdade, 18);
        System.out.println("Testando classe JPQL metodo: addParametroMaiorIgual etapa 03");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.idade>=18"));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroMaiorIgual(chaveNascimento, nascimento);
        System.out.println("Testando classe JPQL metodo: addParametroMaiorIgual etapa 04");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.nascimento>='1989-09-15'"));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroMaiorIgual(chaveSegundosDeVida, 9223372036854775807L);
        System.out.println("Testando classe JPQL metodo: addParametroMaiorIgual etapa 05");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.segundosDeVida>=9223372036854775807"));

    }

    @Test
    public void testAddParametroMenorIgual() {

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroMenorIgual(chaveNome, "Teste");
        System.out.println("Testando classe JPQL metodo: addParametroMenorIgual etapa 01");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.nome<='Teste'"));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroMenorIgual(chaveIdade, "18");
        System.out.println("Testando classe JPQL metodo: addParametroMenorIgual etapa 02");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.idade<='18'"));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroMenorIgual(chaveIdade, 18);
        System.out.println("Testando classe JPQL metodo: addParametroMenorIgual etapa 03");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.idade<=18"));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroMenorIgual(chaveNascimento, nascimento);
        System.out.println("Testando classe JPQL metodo: addParametroMenorIgual etapa 04");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.nascimento<='1989-09-15'"));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroMenorIgual(chaveSegundosDeVida, 9223372036854775807L);
        System.out.println("Testando classe JPQL metodo: addParametroMenorIgual etapa 05");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.segundosDeVida<=9223372036854775807"));

    }

    @Test
    public void testAddParametroMenor() {

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroMenor(chaveNome, "Teste");
        System.out.println("Testando classe JPQL metodo: addParametroMenor etapa 01");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.nome<'Teste'"));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroMenor(chaveIdade, "18");
        System.out.println("Testando classe JPQL metodo: addParametroMenor etapa 02");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.idade<'18'"));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroMenor(chaveIdade, 18);
        System.out.println("Testando classe JPQL metodo: addParametroMenor etapa 03");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.idade<18"));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroMenor(chaveNascimento, nascimento);
        System.out.println("Testando classe JPQL metodo: addParametroMenor etapa 04");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.nascimento<'1989-09-15'"));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroMenor(chaveSegundosDeVida, 9223372036854775807L);
        System.out.println("Testando classe JPQL metodo: addParametroMenor etapa 05");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.segundosDeVida<9223372036854775807"));

    }

    @Test
    public void testAddParametroCompararDuasChaves() {

        jpql = new JPQL(new ModeloDeTeste());
        System.out.println("Testando classe JPQL metodo: addParametroCompararDuasChaves etapa 01");
        assertEquals(true, jpql.getCondicaoWhere().equals(""));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroCompararDuasChaves("chaveA", "chaveB", "<");
        System.out.println("Testando classe JPQL metodo: addParametroCompararDuasChaves etapa 02");
        assertEquals(true, jpql.getCondicaoWhere().equals(" WHERE objeto.chaveA < objeto.chaveB"));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroCompararDuasChaves("chaveA", "chaveB", ">");
        System.out.println("Testando classe JPQL metodo: addParametroCompararDuasChaves etapa 03");
        assertEquals(true, jpql.getCondicaoWhere().equals(" WHERE objeto.chaveA > objeto.chaveB"));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroCompararDuasChaves("chaveA", "chaveB", "<=");
        System.out.println("Testando classe JPQL metodo: addParametroCompararDuasChaves etapa 04");
        assertEquals(true, jpql.getCondicaoWhere().equals(" WHERE objeto.chaveA <= objeto.chaveB"));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroCompararDuasChaves("chaveA", "chaveB", ">=");
        System.out.println("Testando classe JPQL metodo: addParametroCompararDuasChaves etapa 05");
        assertEquals(true, jpql.getCondicaoWhere().equals(" WHERE objeto.chaveA >= objeto.chaveB"));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroCompararDuasChaves("chaveA", "chaveB", "!=");
        System.out.println("Testando classe JPQL metodo: addParametroCompararDuasChaves etapa 06");
        assertEquals(true, jpql.getCondicaoWhere().equals(" WHERE objeto.chaveA != objeto.chaveB"));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroCompararDuasChaves("chaveA", "chaveB", "=");
        System.out.println("Testando classe JPQL metodo: addParametroCompararDuasChaves etapa 07");
        assertEquals(true, jpql.getCondicaoWhere().equals(" WHERE objeto.chaveA = objeto.chaveB"));

    }

    @Test
    public void testAddOrderBy() {

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addOrderBy(chaveNome, "ASC");
        System.out.println(jpql.construirSelect());
        System.out.println("Testando classe JPQL metodo: addOrderBy etapa 01");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto ORDER BY objeto.nome ASC"));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addOrderBy(chaveNome, "DESC");
        System.out.println(jpql.construirSelect());
        System.out.println("Testando classe JPQL metodo: addOrderBy etapa 02");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto ORDER BY objeto.nome DESC"));

    }

    @Test
    public void testGetCondicaoWhere() {

        jpql = new JPQL(new ModeloDeTeste());
        System.out.println("Testando classe JPQL metodo: getCondicaoWhere etapa 01");
        assertEquals(true, jpql.getCondicaoWhere().equals(""));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroIgual(chaveNome, "Teste");
        System.out.println("Testando classe JPQL metodo: getCondicaoWhere etapa 02");
        assertEquals(true, jpql.getCondicaoWhere().equals(" WHERE objeto.nome='Teste'"));

    }

    @Test
    public void testConstruirSelect() {

        jpql = new JPQL(new ModeloDeTeste());
        System.out.println("Testando classe JPQL metodo: construirSelect etapa 01");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto"));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroIgual(chaveNome, "Teste");
        System.out.println("Testando classe JPQL metodo: construirSelect etapa 02");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.nome='Teste'"));

    }

    @Test
    public void testConstruirSelectDistinct() {

        String query;

        query = "SELECT p FROM ModeloDeTeste p WHERE p.id IN (SELECT MAX(p2.id) FROM ModeloDeTeste p2 GROUP BY p2.nome) ORDER BY p.nome ASC";
        jpql = new JPQL(new ModeloDeTeste());
        System.out.println("Testando classe JPQL metodo: construirSelectDistinct etapa 01");
        assertEquals(true, jpql.construirSelectDistinct("nome").equals(query));

        query = "SELECT p FROM ModeloDeTeste p WHERE p.id IN (SELECT MAX(p2.id) FROM ModeloDeTeste p2 GROUP BY p2.telefone) ORDER BY p.telefone ASC";
        jpql = new JPQL(new ModeloDeTeste());
        System.out.println("Testando classe JPQL metodo: construirSelectDistinct etapa 02");
        assertEquals(true, jpql.construirSelectDistinct("telefone").equals(query));

        jpql = new JPQL(new ModeloDeTeste());
        System.out.println("Testando classe JPQL metodo: construirSelectDistinct etapa 03");
        assertEquals(true, isNull(jpql.construirSelectDistinct(null)));

        jpql = new JPQL(new ModeloDeTeste());
        System.out.println("Testando classe JPQL metodo: construirSelectDistinct etapa 04");
        assertEquals(true, isNull(jpql.construirSelectDistinct("")));

    }

}
