package faeterj.prj.foxTurismo.telas;

import faeterj.prj.foxTurismo.banco.RepositorioDados;
import faeterj.prj.foxTurismo.negocio.Bilhete;
import faeterj.prj.foxTurismo.negocio.Passageiro;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.sql.SQLException;

public class TelaAtualizarDados extends JPanel {

    private JPanel cardPanel;
    private JTextField campoNome;
    private JTextField campoCPF;
    private JLabel nome;
    private JLabel cpf;
    private CardLayout cardLayout;
    private JPanel TelaAtualizarDados;
    private JButton botaoVoltar = new JButton("Voltar");
    private JButton botaoSalvar = new JButton("Salvar");
    private Bilhete bilhete;
    private Passageiro passageiro;
    private TelaInformacoesBilhete telaInformacoesBilhete;
    private RepositorioDados repositorioDados = RepositorioDados.getInstancia();

    public TelaAtualizarDados() {
        this.cardPanel = new JPanel();
        this.cardLayout = new CardLayout();
        this.cardPanel.setLayout(this.cardLayout);
        this.setBackground((Color.decode("#EFC2B0")));

        add(getTelaAtualizarDados());
    }

    private JPanel getTelaAtualizarDados(){
        if (this.TelaAtualizarDados == null) {
            this.TelaAtualizarDados = new JPanel();
            this.TelaAtualizarDados.setLayout(new GridLayout(3, 1));

            nome = new JLabel("<html><body>Informe seu nome<br></html></body>");
            nome.setFont(new Font("Serif", Font.PLAIN, 20));
            this.TelaAtualizarDados.add(nome,BorderLayout.CENTER);

            cpf = new JLabel("<html><body> Informe seu CPF<br></html></body>");
            cpf.setFont(new Font("Serif", Font.PLAIN, 20));
            this.TelaAtualizarDados.add(cpf,BorderLayout.CENTER);

            campoNome = new JTextField(15);
            Border line = BorderFactory.createLineBorder(new Color(80, 80, 80));
            campoNome.setBorder(line);
            this.TelaAtualizarDados.add(campoNome, BorderLayout.CENTER);

            campoCPF = new JTextField(11);
            campoCPF.setBorder(line);
            this.TelaAtualizarDados.add(campoCPF, BorderLayout.CENTER);

            botaoSalvar.addActionListener((a) -> {
                if(!campoNome.getText().isBlank() || !campoCPF.getText().isBlank()) {
                    if (botaoSalvar.isEnabled()) {
                        passageiro = new Passageiro();
                        passageiro.setId(bilhete.getPassageiro().getId());
                        passageiro.setNome(campoNome.getText());
                        passageiro.setCpf(campoCPF.getText());
                        try {
                            repositorioDados.atualizarPassageiro(passageiro);
                            passageiro = repositorioDados.buscarPassageiro(bilhete.getPassageiro().getId());
                            bilhete.setPassageiro(passageiro);
                            telaInformacoesBilhete.removeAll();
                            telaInformacoesBilhete.setBilhete(bilhete);
                            telaInformacoesBilhete.setLabelInformacoes();
                        } catch (SQLException | ClassNotFoundException e) {
                            JOptionPane.showMessageDialog(null, "Não foi possível atualizar seus dados, tente novamente");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Os campos não foram preenchidos, tente novamente");
                }
            });

            this.TelaAtualizarDados.add(botaoVoltar, BorderLayout.CENTER);
            this.TelaAtualizarDados.add(botaoSalvar, BorderLayout.CENTER);
        }
        return this.TelaAtualizarDados;
    }

    private void exibe(String nome) { this.cardLayout.show(this.cardPanel, nome);}

    public JButton getBotaoVoltar() {return botaoVoltar;}
    public JButton getBotaoSalvar() {return botaoSalvar;}
    public JTextField getCampoNome() {return campoNome;}
    public JTextField getCampoCPF() {return campoCPF;}
    public void setTelaInformacoesBilhete(TelaInformacoesBilhete telaInformacoesBilhete) {this.telaInformacoesBilhete = telaInformacoesBilhete;}
    public void setBilhete(Bilhete bilhete) {this.bilhete = bilhete; }

}
