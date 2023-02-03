package br.com.salomaotech.sistema.algoritmos;

public class ValidaStringIsEmpty {

    /**
     * Valida se uma String está vazia
     *
     * @param valor String
     * @return true a String é vazia
     */
    public static boolean isEmpty(String valor) {

        try {

            return valor.length() == 0;

        } catch (Exception ex) {

            return true;

        }

    }

}
