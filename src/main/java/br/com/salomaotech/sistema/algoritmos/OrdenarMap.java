package br.com.salomaotech.sistema.algoritmos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class OrdenarMap {

    public static LinkedHashMap ordenarBigDecimal(Map mapOriginal) {

        LinkedHashMap mapRetorno = new LinkedHashMap();
        List<Map.Entry<String, BigDecimal>> lista = new ArrayList<>(mapOriginal.entrySet());
        lista.sort(Map.Entry.comparingByValue());
        Collections.reverse(lista);

        lista.forEach(map -> {
            mapRetorno.put(map.getKey(), map.getValue());
        });

        return mapRetorno;

    }

}
