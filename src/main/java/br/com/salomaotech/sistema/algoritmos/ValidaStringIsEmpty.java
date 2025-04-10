package br.com.salomaotech.sistema.algoritmos;

public class ValidaStringIsEmpty {

    /**
     * Valida se um objeto é uma String vazia ou um objeto nulo
     *
     * @param valor Object
     * @return true se for String vazia ou objeto nulo
     */
    public static boolean isEmpty(Object valor) {

        if (valor == null) {
            return true;
        }

        if (valor instanceof String) {
            return ((String) valor).isEmpty();
        }

        return false;

    }

}
