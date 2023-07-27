package br.com.salomaotech.genesys.controller.ativador;

import br.com.salomaotech.genesys.view.JFativador;
import br.com.salomaotech.sistema.algoritmos.ValidaStringIsEmpty;

public class AtivadorValidador {

    private final JFativador view;

    public AtivadorValidador(JFativador view) {
        this.view = view;
    }

    public boolean isValido() {

        return !ValidaStringIsEmpty.isEmpty(view.jTchave.getText());

    }

}
