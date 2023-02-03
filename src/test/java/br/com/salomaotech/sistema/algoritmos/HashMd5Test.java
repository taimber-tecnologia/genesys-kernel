package br.com.salomaotech.sistema.algoritmos;

import org.junit.Test;
import static org.junit.Assert.*;

public class HashMd5Test {

    @Test
    public void testCifrar() {

        System.out.println("Testando classe HashMd5 metodo: cifrar etapa 01");
        assertEquals(true, HashMd5.cifrar("123456").equals("e10adc3949ba59abbe56e057f20f883e"));

        System.out.println("Testando classe HashMd5 metodo: cifrar etapa 02");
        assertEquals(true, HashMd5.cifrar("ABC").equals("902fbdd2b1df0c4f70b4a5d23525e932"));

    }

}
