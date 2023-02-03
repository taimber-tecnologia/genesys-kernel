package br.com.salomaotech.sistema.algoritmos;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class OrdenarMapTest {

    @Test
    public void testOrdenarBigDecimal() {

        /* map a ser ordenado */
        Map map1 = new LinkedHashMap();
        map1.put("valor1", new BigDecimal(100));
        map1.put("valor2", new BigDecimal(40));
        map1.put("valor3", new BigDecimal(35));
        map1.put("valor4", new BigDecimal(255));
        map1 = OrdenarMap.ordenarBigDecimal(map1);

        System.out.println("Testando classe OrdenarMap metodo: ordenarBigDecimal");
        assertEquals(true, map1.toString().equals("{valor4=255, valor1=100, valor2=40, valor3=35}"));

    }

}
