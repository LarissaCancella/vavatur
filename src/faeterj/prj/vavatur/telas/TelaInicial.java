package faeterj.prj.vavatur.telas;

import faeterj.prj.vavatur.Login;

import javax.swing.*;

public class TelaInicial extends JFrame {

    public TelaInicial() {
        JMenuBar menubar = new JMenuBar();
        menubar.add(new JMenu("Arquivo"));
        setJMenuBar(menubar);
        setSize(300, 300);
        setLocationRelativeTo(null);
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        Login login = new Login();
        login.pack();
        login.setLocationRelativeTo(null);
        login.setModal(true);
        login.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        login.setVisible(true);
    }


}
