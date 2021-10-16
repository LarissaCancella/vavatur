package faeterj.prj.vavatur.telas;

import javax.swing.*;
import java.awt.*;

public class TelaBilhete extends JPanel {

    private JButton botaoBuscarBilhete;

    public TelaBilhete() {
        setLayout(new FlowLayout());
        add(new JLabel("Tela 2"));
        add(new JLabel("Bilhete:"));
        JTextField campoBilhete = new JTextField(5);
        botaoBuscarBilhete = new JButton("Buscar bilhete");
        add(campoBilhete);
        add(botaoBuscarBilhete);
    }

    public JButton getBotaoBuscarBilhete() {
        return botaoBuscarBilhete;
    }

}
