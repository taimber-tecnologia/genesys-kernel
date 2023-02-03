package br.com.salomaotech.sistema.algoritmos;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashMd5 {

    /**
     * Cifra um texto com o algoritmo MD5
     *
     * @param texto Texto a ser cifrado
     * @return String no formado MD5
     */
    public static String cifrar(String texto) {

        try {

            /* define o tipo de algoritmo como MD5 */
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(texto.getBytes(), 0, texto.length());

            /* converte para BigInteger */
            BigInteger integer = new BigInteger(1, messageDigest.digest());

            /* retorna */
            return integer.toString(16);

        } catch (NoSuchAlgorithmException ex) {

            return null;

        }

    }

}
