package br.com.salomaotech.sistema.jpa;

import java.util.Calendar;
import org.junit.Test;
import static org.junit.Assert.*;

public class JPQLTest {

    private JPQL jpql;
    private final String chaveNome = "nome";
    private final String chaveIdade = "idade";
    private final String chaveNascimento = "nascimento";
    private final String chaveSegundosDeVida = "segundosDeVida";
    private final Calendar calendar = Calendar.getInstance();

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
        jpql.addParametroIgual(chaveNascimento, calendar);
        System.out.println("Testando classe JPQL metodo: addParametroIgual etapa 04");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.nascimento='" + calendar + "'"));

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
        jpql.addParametroDiferente(chaveNascimento, calendar);
        System.out.println("Testando classe JPQL metodo: addParametroDiferente etapa 04");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.nascimento!='" + calendar + "'"));

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
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.nome LIKE '%Teste%'"));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroLike(chaveIdade, "18");
        System.out.println("Testando classe JPQL metodo: addParametroLike etapa 02");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.idade LIKE '%18%'"));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroLike(chaveIdade, 18);
        System.out.println("Testando classe JPQL metodo: addParametroLike etapa 03");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.idade LIKE '%18%'"));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroLike(chaveNascimento, calendar);
        System.out.println("Testando classe JPQL metodo: addParametroLike etapa 04");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.nascimento LIKE '%" + calendar + "%'"));

        jpql = new JPQL(new ModeloDeTeste());
        jpql.addParametroLike(chaveSegundosDeVida, 9223372036854775807L);
        System.out.println("Testando classe JPQL metodo: addParametroLike etapa 05");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.segundosDeVida LIKE '%9223372036854775807%'"));

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
        jpql.addParametroMaiorIgual(chaveNascimento, calendar);
        System.out.println("Testando classe JPQL metodo: addParametroMaiorIgual etapa 04");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.nascimento>='" + calendar + "'"));

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
        jpql.addParametroMenorIgual(chaveNascimento, calendar);
        System.out.println("Testando classe JPQL metodo: addParametroMenorIgual etapa 04");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.nascimento<='" + calendar + "'"));

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
        jpql.addParametroMenor(chaveNascimento, calendar);
        System.out.println("Testando classe JPQL metodo: addParametroMenor etapa 04");
        assertEquals(true, jpql.construirSelect().equals("SELECT objeto FROM ModeloDeTeste objeto WHERE objeto.nascimento<'" + calendar + "'"));

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

}
