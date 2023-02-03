package br.com.salomaotech.sistema.algoritmos;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Datas {

    /**
     * Converte um Calendar para uma String no formato dd/MM/yyyy
     *
     * @param calendar Parâmetro
     * @return String no formato dd/MM/yyyy
     */
    public static String calendarParaStringBr(Calendar calendar) {

        try {

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            return formatter.format(calendar.getTime());

        } catch (Exception ex) {

            return null;

        }

    }

    /**
     * Adiciona meses a um Calendar
     *
     * @param calendar Parâmetro
     * @param numeroMesesAdicionar Número de meses a serem adicionados
     * @return Novo Calendar atualizado
     */
    public static Calendar adicionarMesCalendar(Calendar calendar, int numeroMesesAdicionar) {

        try {

            Calendar novoCalendar = (Calendar) calendar.clone();
            novoCalendar.add(Calendar.MONTH, numeroMesesAdicionar);
            return novoCalendar;

        } catch (Exception ex) {

            return null;

        }

    }

    /**
     * Retorna se um Calendar é válida entre 01/01/0001 até 31/12/9998
     *
     * @param calendar Calendar
     * @return true se a data for válida
     */
    public static boolean isCalendarioValido(Calendar calendar) {

        try {

            boolean isAnoValido = calendar.get(Calendar.YEAR) >= 1 && calendar.get(Calendar.YEAR) <= 9998;
            boolean isMesValido = calendar.get(Calendar.MONTH) >= 0 && calendar.get(Calendar.MONTH) <= 11;
            boolean isDiaValido = calendar.get(Calendar.DAY_OF_MONTH) >= 1 && calendar.get(Calendar.DAY_OF_MONTH) <= 31;
            return isAnoValido && isMesValido && isDiaValido;

        } catch (Exception ex) {

            return false;

        }

    }

}
