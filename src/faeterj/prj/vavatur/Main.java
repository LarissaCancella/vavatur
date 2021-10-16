package faeterj.prj.vavatur;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        CardLayoutExemplo cle = new CardLayoutExemplo();

        cle.setVisible(true);
        cle.pack();
        cle.setLocationRelativeTo(null);
        cle.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
