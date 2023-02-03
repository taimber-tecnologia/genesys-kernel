package br.com.salomaotech.sistema.algoritmos;

import org.junit.Test;
import static org.junit.Assert.*;

public class ConverteNumeroParaMoedaBrTest {

    @Test
    public void testConverter() {

        System.out.println("Testando classe ConverteNumeroParaMoedaBr metodo: converter etapa 01");
        assertEquals(true, ConverteNumeroParaMoedaBr.converter("199,99").equals("R$ 199,99"));

        System.out.println("Testando classe ConverteNumeroParaMoedaBr metodo: converter etapa 02");
        assertEquals(true, ConverteNumeroParaMoedaBr.converter("199.99").equals("R$ 199,99"));

        System.out.println("Testando classe ConverteNumeroParaMoedaBr metodo: converter etapa 03");
        assertEquals(true, ConverteNumeroParaMoedaBr.converter("199.999,99").equals("R$ 0,00"));

        System.out.println("Testando classe ConverteNumeroParaMoedaBr metodo: converter etapa 04");
        assertEquals(true, ConverteNumeroParaMoedaBr.converter("199,999,99").equals("R$ 0,00"));

        System.out.println("Testando classe ConverteNumeroParaMoedaBr metodo: converter etapa 05");
        assertEquals(true, ConverteNumeroParaMoedaBr.converter("199,999,999,99").equals("R$ 0,00"));

        System.out.println("Testando classe ConverteNumeroParaMoedaBr metodo: converter etapa 06");
        assertEquals(true, ConverteNumeroParaMoedaBr.converter("199.999.999.99").equals("R$ 0,00"));

        System.out.println("Testando classe ConverteNumeroParaMoedaBr metodo: converter etapa 07");
        assertEquals(true, ConverteNumeroParaMoedaBr.converter("199.999.999,99").equals("R$ 0,00"));

        System.out.println("Testando classe ConverteNumeroParaMoedaBr metodo: converter etapa 08");
        assertEquals(true, ConverteNumeroParaMoedaBr.converter("199.999.999,999,99").equals("R$ 0,00"));

    }

}
