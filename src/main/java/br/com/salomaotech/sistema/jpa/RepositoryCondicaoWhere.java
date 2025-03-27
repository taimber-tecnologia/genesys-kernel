package br.com.salomaotech.sistema.jpa;

public class RepositoryCondicaoWhere {

    private final String operador;
    private final Object valor;

    public RepositoryCondicaoWhere(String operador, Object valor) {

        this.operador = operador;

        // Se o operador for "IS NULL" ou "IS NOT NULL", o valor será a própria condição
        if ("IS NULL".equals(operador) || "IS NOT NULL".equals(operador)) {

            this.valor = operador;

        } else {

            // Se o operador ou o valor forem nulos então o valor é IS NULL
            if (operador == null || valor == null) {

                // Se o valor for null, ajusta para "IS NULL"
                this.valor = "IS NULL";

            } else {

                // Caso contrário, define o valor fornecido
                this.valor = valor;

            }

        }

    }

    public String getOperador() {
        return operador;
    }

    public Object getValor() {
        return valor;
    }

}
