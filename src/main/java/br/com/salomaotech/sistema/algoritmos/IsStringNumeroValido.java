package br.com.salomaotech.sistema.algoritmos;

import java.math.BigDecimal;
import static java.util.Objects.isNull;

public class IsStringNumeroValido {

    public static boolean isNumeroValido(String valor) {

        try {

            return !isNull(new BigDecimal(valor.replace(",", ".")));

        } catch (Exception ex) {

            return false;

        }

    }

}
