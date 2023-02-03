package br.com.salomaotech.sistema.algoritmos;

import java.math.BigDecimal;
import org.junit.Test;
import static org.junit.Assert.*;

public class BigDecimaisTest {

    @Test
    public void testFormatarParaBigDecimal() {

        System.out.println("Testando classe BigDecimais metodo: formatarParaBigDecimal etapa 01");
        assertEquals(true, BigDecimais.formatarParaBigDecimal("199,99").equals(new BigDecimal("199.99")));

        System.out.println("Testando classe BigDecimais metodo: formatarParaBigDecimal etapa 02");
        assertEquals(true, BigDecimais.formatarParaBigDecimal("199,999,99").equals(new BigDecimal("0")));

        System.out.println("Testando classe BigDecimais metodo: formatarParaBigDecimal etapa 03");
        assertEquals(true, BigDecimais.formatarParaBigDecimal("199.999.99").equals(new BigDecimal("0")));

        System.out.println("Testando classe BigDecimais metodo: formatarParaBigDecimal etapa 04");
        assertEquals(true, BigDecimais.formatarParaBigDecimal("199.999,99").equals(new BigDecimal("0")));

        System.out.println("Testando classe BigDecimais metodo: formatarParaBigDecimal etapa 05");
        assertEquals(true, BigDecimais.formatarParaBigDecimal("199.999.999,999,99").equals(new BigDecimal("0")));

        System.out.println("Testando classe BigDecimais metodo: formatarParaBigDecimal etapa 06");
        assertEquals(true, BigDecimais.formatarParaBigDecimal("abc123").equals(new BigDecimal("0")));

    }

    @Test
    public void testFormatarParaBigDecimalComCasaDecimal() {

        System.out.println("Testando classe BigDecimais metodo: formatarParaBigDecimalComCasaDecimal etapa 01");
        assertEquals(true, BigDecimais.formatarParaBigDecimalComCasaDecimal("199,99", 3).equals(new BigDecimal("199.990")));

        System.out.println("Testando classe BigDecimais metodo: formatarParaBigDecimalComCasaDecimal etapa 02");
        assertEquals(true, BigDecimais.formatarParaBigDecimalComCasaDecimal("199,999,99", 3).equals(new BigDecimal("0")));

        System.out.println("Testando classe BigDecimais metodo: formatarParaBigDecimalComCasaDecimal etapa 03");
        assertEquals(true, BigDecimais.formatarParaBigDecimalComCasaDecimal("199.999.99", 3).equals(new BigDecimal("0")));

        System.out.println("Testando classe BigDecimais metodo: formatarParaBigDecimalComCasaDecimal etapa 04");
        assertEquals(true, BigDecimais.formatarParaBigDecimalComCasaDecimal("199.999,99", 3).equals(new BigDecimal("0")));

        System.out.println("Testando classe BigDecimais metodo: formatarParaBigDecimalComCasaDecimal etapa 05");
        assertEquals(true, BigDecimais.formatarParaBigDecimalComCasaDecimal("199.999.999,999,99", 3).equals(new BigDecimal("0")));

        System.out.println("Testando classe BigDecimais metodo: formatarParaBigDecimalComCasaDecimal etapa 06");
        assertEquals(true, BigDecimais.formatarParaBigDecimalComCasaDecimal("abc123", 3).equals(new BigDecimal("0")));

    }

    @Test
    public void testDividir() {

        System.out.println("Testando classe BigDecimais metodo: dividir etapa 01");
        assertEquals(true, BigDecimais.dividir(new BigDecimal("1000"), new BigDecimal("3")).equals(new BigDecimal("333.33")));

        System.out.println("Testando classe BigDecimais metodo: dividir etapa 02");
        assertEquals(true, BigDecimais.dividir(new BigDecimal("1000.32"), new BigDecimal("3")).equals(new BigDecimal("333.44")));

        System.out.println("Testando classe BigDecimais metodo: dividir etapa 03");
        assertEquals(true, BigDecimais.dividir(new BigDecimal("999"), new BigDecimal("2")).equals(new BigDecimal("499.50")));

        System.out.println("Testando classe BigDecimais metodo: dividir etapa 04");
        assertEquals(true, BigDecimais.dividir(new BigDecimal("1000"), new BigDecimal("2")).equals(new BigDecimal("500.00")));

        System.out.println("Testando classe BigDecimais metodo: dividir etapa 05");
        assertEquals(true, BigDecimais.dividir(new BigDecimal("0"), new BigDecimal("2")).equals(new BigDecimal("0.00")));

    }

    @Test
    public void testIsNegativo() {

        System.out.println("Testando classe BigDecimais metodo: isNegativo etapa 01");
        assertEquals(true, BigDecimais.isNegativo(new BigDecimal(-1)));

        System.out.println("Testando classe BigDecimais metodo: isNegativo etapa 02");
        assertEquals(true, BigDecimais.isNegativo(new BigDecimal("-1")));

        System.out.println("Testando classe BigDecimais metodo: isNegativo etapa 03");
        assertEquals(false, BigDecimais.isNegativo(new BigDecimal(0)));

        System.out.println("Testando classe BigDecimais metodo: isNegativo etapa 04");
        assertEquals(false, BigDecimais.isNegativo(new BigDecimal("0")));

        System.out.println("Testando classe BigDecimais metodo: isNegativo etapa 05");
        assertEquals(false, BigDecimais.isNegativo(new BigDecimal(1)));

        System.out.println("Testando classe BigDecimais metodo: isNegativo etapa 06");
        assertEquals(false, BigDecimais.isNegativo(new BigDecimal("1")));

    }

}
