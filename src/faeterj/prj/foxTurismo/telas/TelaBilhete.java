package faeterj.prj.foxTurismo.telas;

import faeterj.prj.foxTurismo.banco.RepositorioDados;
import faeterj.prj.foxTurismo.negocio.Bilhete;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;

public class TelaBilhete extends JPanel {

    Boolean erroAoBuscarBilhete = false;
    private TelaInformacoesBilhete telaInformacoesBilhete;
    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JPanel TelaBilhete;
    JTextField campoBilhete = new JTextField(5);
    private JButton botaoBuscarBilhete= new JButton("buscar Bilhete");
    private RepositorioDados repositorioDados = RepositorioDados.getInstancia();
    private Bilhete bilhete;

    public TelaBilhete() {
        this.cardPanel = new JPanel();
        this.cardLayout = new CardLayout();
        this.cardPanel.setLayout(this.cardLayout);
        this.setBackground((Color.decode("#EFC2B0")));
        add(getTelaBilhete());
    }


    private JPanel getTelaBilhete() {
        if (this.TelaBilhete == null) {
            this.TelaBilhete = new JPanel();
            this.TelaBilhete.setLayout(new BoxLayout(TelaBilhete, BoxLayout.Y_AXIS));
            this.TelaBilhete.setBorder(new EmptyBorder(new Insets(100, 3, 1, 3)));
            this.TelaBilhete.setBackground((Color.decode("#EFC2B0")));

            campoBilhete = new JTextField(5);
            this.TelaBilhete.add(campoBilhete);
            this.TelaBilhete.add(botaoBuscarBilhete);

            if (botaoBuscarBilhete.isEnabled()) {
                botaoBuscarBilhete.addActionListener((e) ->{
                try {
                    bilhete = repositorioDados.buscarBilhetePorCodigo(campoBilhete.getText());
                    telaInformacoesBilhete.removeAll();
                    telaInformacoesBilhete.setBilhete(bilhete);
                    telaInformacoesBilhete.setLabelInformacoes();
                } catch (SQLException | ClassNotFoundException k) {
                    erroAoBuscarBilhete = true;
                    JOptionPane.showMessageDialog(null, "Não foi possivel encontrar o bilhete, verifique os numeros e tente novamente");
                } });
            }
        }
        return this.TelaBilhete;
    }

    public JButton getBotaoBuscarBilhete() {return botaoBuscarBilhete;}
    public Boolean getErroAoBuscarBilhete() {return erroAoBuscarBilhete;}
    public JTextField getCampoBilhete() {return campoBilhete;}
    public void setTelaInformacoesBilhete(TelaInformacoesBilhete telaInformacoesBilhete) {this.telaInformacoesBilhete = telaInformacoesBilhete;}

    private void exibe(String nome) {
        this.cardLayout.show(this.cardPanel, nome);
    }


}