package faeterj.prj.vavatur;

import faeterj.prj.vavatur.telas.TelaBilhete;

import javax.swing.*;
import java.awt.*;

public class CardLayoutExemplo extends JFrame {

    private JPanel cardPanel;
    private CardLayout cardLayout;

    private JPanel telaInicial;
    private TelaBilhete tela2;

    public CardLayoutExemplo() {
        this.cardPanel = new JPanel();
        this.cardLayout = new CardLayout();
        this.cardPanel.setLayout(this.cardLayout);
        this.setContentPane(this.cardPanel);

        add(getTelaInicial(), "a");
        tela2 = new TelaBilhete();
        add(tela2, "b");

        // amarra��o e l�gica entre telas, de navega��o
        tela2.getBotaoBuscarBilhete().addActionListener((e) -> {
            exibe("a");
        });

        exibe("a");
    }

    private void exibe(String nome) {
        this.cardLayout.show(this.cardPanel, nome);
    }

    private JPanel getTelaInicial() {
        if (this.telaInicial == null) {
            this.telaInicial = new JPanel();
            this.telaInicial.setLayout(new FlowLayout());
            this.telaInicial.add(new JLabel("Tela 1"));
            JButton botaoRealizarChecking = new JButton("Realizar checking");
            botaoRealizarChecking.addActionListener((a) -> {
                exibe("b");
            });
            this.telaInicial.add(botaoRealizarChecking);
        }
        return this.telaInicial;
    }

}
