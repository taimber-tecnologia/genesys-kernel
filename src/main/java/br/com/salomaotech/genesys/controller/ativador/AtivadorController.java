package br.com.salomaotech.genesys.controller.ativador;

import br.com.salomaotech.genesys.model.ativador.Ativador;
import br.com.salomaotech.genesys.view.JFativador;
import br.com.salomaotech.sistema.swing.MudaIconeJframe;

public class AtivadorController {

    private final JFativador view = new JFativador();
    private AtivadorMetodos ativadorMetodos;
    private AtivadorEventos ativadorEventos;

    public void construir(String nomeSistema) {

        /* valida se o sistema não está ativado */
        if (!new Ativador(nomeSistema).isAtivado()) {

            /* metodos */
            ativadorMetodos = new AtivadorMetodos(view, nomeSistema);
            ativadorMetodos.addPopUpMenu();
            ativadorMetodos.exibeDiasRestantes();

            /* eventos */
            ativadorEventos = new AtivadorEventos(view);
            ativadorEventos.setAtivadorMetodos(ativadorMetodos);
            ativadorEventos.addEventos();

            /* view */
            new MudaIconeJframe().alterar("key64x", view);
            view.setVisible(true);
            view.jTchave.requestFocus();

        }

    }

}
