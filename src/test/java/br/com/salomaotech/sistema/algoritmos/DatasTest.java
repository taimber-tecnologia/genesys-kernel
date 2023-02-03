package br.com.salomaotech.sistema.algoritmos;

import java.util.Calendar;
import org.junit.Test;
import static org.junit.Assert.*;

public class DatasTest {

    @Test
    public void testCalendarParaStringBr() {

        Calendar calendario1 = Calendar.getInstance();
        calendario1.set(Calendar.YEAR, 1989);
        calendario1.set(Calendar.MONTH, Calendar.SEPTEMBER);
        calendario1.set(Calendar.DAY_OF_MONTH, 15);

        System.out.println("Testando classe Datas metodo: dateParaStringBr etapa 01");
        assertEquals(true, Datas.calendarParaStringBr(calendario1).equals("15/09/1989"));

        calendario1 = Calendar.getInstance();
        calendario1.set(Calendar.YEAR, 2022);
        calendario1.set(Calendar.MONTH, Calendar.JANUARY);
        calendario1.set(Calendar.DAY_OF_MONTH, 01);

        System.out.println("Testando classe Datas metodo: dateParaStringBr etapa 02");
        assertEquals(true, Datas.calendarParaStringBr(calendario1).equals("01/01/2022"));

    }

    @Test
    public void testAdicionarMesCalendar() {

        Calendar calendario1 = Calendar.getInstance();
        calendario1.set(Calendar.YEAR, 1989);
        calendario1.set(Calendar.MONTH, Calendar.SEPTEMBER);
        calendario1.set(Calendar.DAY_OF_MONTH, 15);

        Calendar calendario2 = Datas.adicionarMesCalendar(calendario1, 0);
        System.out.println("Testando classe Datas metodo: adicionaMesData etapa 01");
        assertEquals(true, calendario2.get(Calendar.YEAR) == 1989);
        assertEquals(true, calendario2.get(Calendar.MONTH) == calendario1.get(Calendar.MONTH));
        assertEquals(true, calendario2.get(Calendar.DAY_OF_MONTH) == 15);

        calendario1 = Calendar.getInstance();
        calendario1.set(Calendar.YEAR, 1989);
        calendario1.set(Calendar.MONTH, Calendar.SEPTEMBER);
        calendario1.set(Calendar.DAY_OF_MONTH, 15);

        calendario2 = Datas.adicionarMesCalendar(calendario1, 1);
        System.out.println("Testando classe Datas metodo: adicionaMesData etapa 02");
        assertEquals(true, calendario2.get(Calendar.YEAR) == 1989);
        assertEquals(true, calendario2.get(Calendar.MONTH) == (calendario1.get(Calendar.MONTH) + 1));
        assertEquals(true, calendario2.get(Calendar.DAY_OF_MONTH) == 15);

    }

    @Test
    public void testIsCalendarioValido() {

        Calendar calendario1 = Calendar.getInstance();
        calendario1.set(Calendar.YEAR, 1989);
        calendario1.set(Calendar.MONTH, Calendar.SEPTEMBER);
        calendario1.set(Calendar.DAY_OF_MONTH, 15);
        System.out.println("Testando classe Datas metodo: isCalendarioValido etapa 01");
        assertEquals(true, Datas.isCalendarioValido(calendario1));

        calendario1 = Calendar.getInstance();
        calendario1.set(Calendar.YEAR, 9999);
        calendario1.set(Calendar.MONTH, Calendar.SEPTEMBER);
        calendario1.set(Calendar.DAY_OF_MONTH, 15);
        System.out.println("Testando classe Datas metodo: isCalendarioValido etapa 02");
        assertEquals(false, Datas.isCalendarioValido(calendario1));

    }

}
