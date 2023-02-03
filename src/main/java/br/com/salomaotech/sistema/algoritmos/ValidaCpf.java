package br.com.salomaotech.sistema.algoritmos;

import br.com.caelum.stella.validation.CPFValidator;

public class ValidaCpf {

    /**
     * Retorna se um CPF é válido
     *
     * @param cpf CPF no formato 000.000.000-00 ou somente 00000000000
     * @return true se o CPF for válido
     */
    public static boolean isValido(String cpf) {

        try {

            cpf = cpf.replace(".", "");
            cpf = cpf.replace("-", "");

        } catch (Exception ex) {

        }

        return new CPFValidator().invalidMessagesFor(cpf).isEmpty();

    }

}
