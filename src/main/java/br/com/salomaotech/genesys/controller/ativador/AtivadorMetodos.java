package br.com.salomaotech.genesys.controller.ativador;

import br.com.salomaotech.genesys.model.ativador.Ativador;
import br.com.salomaotech.genesys.view.JFativador;
import br.com.salomaotech.sistema.swing.PopUp;
import java.util.Timer;
import java.util.TimerTask;

public class AtivadorMetodos {

    private final JFativador view;
    private final int segundosExibirJanela = 30;

    public AtivadorMetodos(JFativador view) {
        this.view = view;
        addTimerLembreteAtivacao();
    }

    public void addPopUpMenu() {

        PopUp popUp = new PopUp();
        popUp.adicionarMenu(view.jTchave);

    }

    public boolean isAtivar(String chave) {

        return new Ativador().ativar(chave);

    }

    public void exibeDiasRestantes() {

        view.jLdiasRestantes.setText(String.valueOf(new Ativador().getDiasRestantes()));

    }

    private void addTimerLembreteAtivacao() {

        Timer timer = new Timer();

        TimerTask timerTask = new TimerTask() {

            @Override
            public void run() {

                if (new Ativador().isExpirou()) {

                    view.setVisible(true);

                } else {

                    this.cancel();

                }

            }

        };

        /* atualiza o timer */
        timer.schedule(timerTask, 0, (segundosExibirJanela * 1000));

    }

}
