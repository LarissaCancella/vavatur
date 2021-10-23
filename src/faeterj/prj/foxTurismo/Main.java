package faeterj.prj.foxTurismo;

import faeterj.prj.foxTurismo.telas.TelaInicial;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        TelaInicial telaInicial = new TelaInicial();

        telaInicial.setVisible(true);
        telaInicial.pack();
        telaInicial.setLocationRelativeTo(null);
        telaInicial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
