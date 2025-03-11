package br.com.salomaotech.sistema.algoritmos;

import java.math.BigDecimal;

public class IsStringNumeroValidoPositivo {

    public static boolean isNumeroPositivo(String valor) {

        if (IsStringNumeroValido.isNumeroValido(valor)) {

            try {

                return new BigDecimal(valor.replace(",", ".")).compareTo(BigDecimal.ZERO) >= 0;

            } catch (NumberFormatException ex) {

                return false;

            }

        } else {

            return false;

        }

    }

}
