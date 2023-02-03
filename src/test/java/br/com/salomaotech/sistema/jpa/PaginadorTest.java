package br.com.salomaotech.sistema.jpa;

import javax.swing.JComboBox;
import org.junit.Test;
import static org.junit.Assert.*;

public class PaginadorTest {

    public PaginadorTest() {

        /* deleta todos os registros */
        new Repository(new ModeloDeTeste()).deleteTodos();

        /* simula cadastro */
        int i = 0;

        for (i = 0; i <= 105; i++) {

            ModeloDeTeste modelo = new ModeloDeTeste();
            modelo.setNome("Teste " + i);
            new Repository(modelo).save();

        }

    }

    @Test
    public void testGetPageNumber() {

        /* ao iniciar o getPageNumber deve ser 1 */
        JComboBox jc = new JComboBox();
        Paginador paginador = new Paginador(new JPQL(new ModeloDeTeste()), jc, new ModeloDeTeste());
        System.out.println("Testando classe Paginador metodo: getPageNumber etapa 01");
        assertEquals(true, paginador.getPageNumber() == 1);

        /* ao selecionar o último item da JComboBox o getPageNumber deve ser o valor selecionado na JComboBox */
        jc.setSelectedItem(jc.getItemCount());
        paginador = new Paginador(new JPQL(new ModeloDeTeste()), jc, new ModeloDeTeste());
        System.out.println("Testando classe Paginador metodo: getPageNumber etapa 02");
        assertEquals(true, paginador.getPageNumber() == jc.getItemCount());

        /* ao selecionar o penúltimo item da JComboBox o getPageNumber deve ser o valor selecionado na JComboBox */
        jc.setSelectedItem(jc.getItemCount() - 1);
        paginador = new Paginador(new JPQL(new ModeloDeTeste()), jc, new ModeloDeTeste());
        System.out.println("Testando classe Paginador metodo: getPageNumber etapa 03");
        assertEquals(true, paginador.getPageNumber() == jc.getItemCount() - 1);

        /* ao selecionar um índice da JComboBox que não existe, então o getPageNumber deve ser 1 por padrão */
        jc = new JComboBox();
        paginador = new Paginador(new JPQL(new ModeloDeTeste()), jc, new ModeloDeTeste());
        jc.setSelectedItem(1000);
        System.out.println("Testando classe Paginador metodo: getPageNumber etapa 04");
        assertEquals(true, paginador.getPageNumber() == 1);

    }

    @Test
    public void testGetPageSize() {

        Paginador paginador = new Paginador(new JPQL(new ModeloDeTeste()), new JComboBox(), new ModeloDeTeste());
        System.out.println("Testando classe Paginador metodo: getPageSize");
        assertEquals(true, paginador.getPageSize() == 100);

    }

}
