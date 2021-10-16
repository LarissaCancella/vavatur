package faeterj.prj.vavatur;

import javax.swing.*;

public class Login extends JDialog {

    private JTextField campoSenha;

    public Login() {
        JPanel painel = new JPanel();

        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        this.campoSenha = new JTextField(8);
        painel.add(campoSenha);

        JButton login = new JButton("Entrar");
        login.addActionListener((e) -> {
            if (this.campoSenha.getText().equals("123")) {
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Falha no login", "Ops", JOptionPane.ERROR_MESSAGE);
            }
        });
        painel.add(login);
        setContentPane(painel);
    }
}
