package br.com.salomaotech.sistema.jpa;

import br.com.salomaotech.sistema.patterns.Modelo;
import javax.swing.JComboBox;

public class Paginador {

    private int pageNumber;
    private final int pageSize = 100;

    public Paginador(JPQL jpql, JComboBox jCpaginador, Modelo modelo) {

        /* primeiro pega o pageNumber atual antes de popular na JComboBox */
        try {

            pageNumber = (int) jCpaginador.getSelectedItem();

        } catch (Exception ex) {

            pageNumber = 1;

        }

        /* remove os itens antigos */
        jCpaginador.removeAllItems();

        /* calcula o número de páginas */
        int numeroDePaginas = (int) (new Repository(modelo).countTodos(jpql.getCondicaoWhere()) / pageSize) + 1;

        /* popula as novas páginas */
        for (int i = 1; i <= numeroDePaginas; i++) {

            jCpaginador.addItem(i);

        }

        /* restaura o último pageNumber selecionado na JComboBox */
        jCpaginador.setSelectedItem(pageNumber);

    }

    public int getPageNumber() {
        return pageNumber;
    }

    public int getPageSize() {
        return pageSize;
    }

}
