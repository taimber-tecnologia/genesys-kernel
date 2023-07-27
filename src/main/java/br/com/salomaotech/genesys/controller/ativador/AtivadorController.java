package br.com.salomaotech.genesys.controller.ativador;

import br.com.salomaotech.genesys.model.ativador.Ativador;
import br.com.salomaotech.genesys.view.JFativador;
import br.com.salomaotech.sistema.swing.MudaIconeJframe;

public class AtivadorController {

    private final JFativador view = new JFativador();
    private final AtivadorMetodos ativadorMetodos = new AtivadorMetodos(view);
    private final AtivadorEventos ativadorEventos = new AtivadorEventos(view);

    public void construir() {

        /* valida se o sistema não está ativado */
        if (!new Ativador().isAtivado()) {

            /* metodos */
            ativadorMetodos.addPopUpMenu();
            ativadorMetodos.exibeDiasRestantes();

            /* eventos */
            ativadorEventos.setAtivadorMetodos(ativadorMetodos);
            ativadorEventos.addEventos();

            /* view */
            new MudaIconeJframe().alterar("key64x", view);
            view.setVisible(true);
            view.jTchave.requestFocus();

        }

    }

}
