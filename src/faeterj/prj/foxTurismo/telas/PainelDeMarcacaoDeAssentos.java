package faeterj.prj.foxTurismo.telas;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PainelDeMarcacaoDeAssentos extends JPanel{

    private List<JButton> botoes = new ArrayList<>();
    private Integer assentoSelecionado = null;

    private List<ObservadoresAssento> listeners = new ArrayList<>();

    public void addListener(ObservadoresAssento listener) {
        this.listeners.add(listener);
    }

    public void removeListener(ObservadoresAssento listener) {
        this.listeners.remove(listener);
    }

    public Integer getAssentoSelecionado() {
        return this.assentoSelecionado;
    }

    private ActionListener aoClicarNosBotoesDeAssento = (e) -> {
        if (this.assentoSelecionado != null) {
            this.setAssento(this.assentoSelecionado, Situacao.LIVRE);
        }
        this.assentoSelecionado = botoes.indexOf(e.getSource()) + 1;
        this.setAssento(this.assentoSelecionado, Situacao.SELECIONADO);

        for (ObservadoresAssento listener: this.listeners) {
            listener.oAssentoSelecionadoMudou(this.assentoSelecionado);
        }
    };

    public void PainelDeMarcacaoDeAssento(int quantidade) {

        int numColunas = quantidade % 2;

        GridLayout leiaute = new GridLayout(quantidade / 4, 4);
        leiaute.setHgap(10);
        leiaute.setVgap(5);
        setLayout(leiaute);

        for (int i = 0; i < quantidade; i++) {
            JButton b = new JButton("" + (i + 1));
            b.addActionListener(this.aoClicarNosBotoesDeAssento);
            add(b);
            botoes.add(b);
            setAssento(i + 1, Situacao.LIVRE);
        }
    }

    public void setAssento(int assento, Situacao situacao) {
        JButton b = botoes.get(assento - 1);
        if (situacao == Situacao.OCUPADO) {
            b.setBackground(Color.RED);
            b.setEnabled(false);
        } else if (situacao == Situacao.LIVRE) {
            b.setBackground(Color.GREEN);
            b.setEnabled(true);
        } else if (situacao == Situacao.SELECIONADO) {
            b.setBackground(Color.YELLOW);
        }
    }
}


