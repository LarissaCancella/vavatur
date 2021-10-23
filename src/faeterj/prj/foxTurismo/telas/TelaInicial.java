package faeterj.prj.foxTurismo.telas;

import faeterj.prj.foxTurismo.negocio.Bilhete;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TelaInicial extends JFrame {

    private JPanel cardPanel;
    private CardLayout cardLayout;
    private JPanel telaInicial;
    private TelaBilhete telaBilhete;
    private TelaInformacoesBilhete telaInformacoesBilhete;
    private TelaAtualizarDados telaAtualizarDados;
    private TelaMarcacaoAssento telaMarcacaoAssento;

    public TelaInicial() {

        this.cardPanel = new JPanel();
        this.cardLayout = new CardLayout();
        this.cardPanel.setLayout(this.cardLayout);
        this.setContentPane(this.cardPanel);

        add(getTelaInicial(), "a");
        add(telaBilhete, "b");
        add(telaInformacoesBilhete, "c");
        add(telaMarcacaoAssento, "d");
        add(telaAtualizarDados, "e");

        exibe("a");
    }

    private JPanel getTelaInicial() {
        if (this.telaInicial == null) {
            this.telaInicial = new JPanel();

            telaAtualizarDados = new TelaAtualizarDados();
            telaMarcacaoAssento = new TelaMarcacaoAssento();
            telaInformacoesBilhete = new TelaInformacoesBilhete();
            telaInformacoesBilhete.setTelaAtualizarDados(telaAtualizarDados);
            telaInformacoesBilhete.setTelaMarcacaoAssento(telaMarcacaoAssento);
            telaAtualizarDados.setTelaInformacoesBilhete(telaInformacoesBilhete);
            telaBilhete = new TelaBilhete();
            telaBilhete.setTelaInformacoesBilhete(telaInformacoesBilhete);
            telaMarcacaoAssento.setTelaBilhete(telaBilhete);

            this.telaInicial.setLayout(new BorderLayout(10,10));
            this.telaInicial.setBorder(new EmptyBorder(new Insets(1, 3, 1, 3)));
            try {
                BufferedImage myPicture = ImageIO.read(getClass().getClassLoader().getResource("Foxturismo.png"));
                JLabel picLabel = new JLabel(new ImageIcon(myPicture));
                this.telaInicial.add(picLabel);
            } catch (IOException e) {
                e.printStackTrace();
            }
            this.telaInicial.setBackground(Color.decode("#EFC2B0"));

            JButton botaoRealizarChecking = new JButton("Realizar checking");

            this.telaInicial.add(botaoRealizarChecking,BorderLayout.SOUTH);

            botaoRealizarChecking.setSize(15,15);

            botaoRealizarChecking.addActionListener((a) -> {
                exibe("b");
            });

            telaBilhete.getBotaoBuscarBilhete().addActionListener((e)->{
                if(!telaBilhete.getCampoBilhete().getText().isBlank()) {
                    if(!telaBilhete.getErroAoBuscarBilhete()) {
                        exibe("c");
                    } else {
                        JOptionPane.showMessageDialog(null, "Bilhete não encontrado");
                        exibe("a");
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Bilhete não foi preenchido");
                    exibe("a");
                }

            });

            telaInformacoesBilhete.getBotaoProximo().addActionListener((g)->{
                exibe("d");
            });

            telaInformacoesBilhete.getBotaoAttDados().addActionListener((f)->{
                exibe("e");
            });

            telaAtualizarDados.getBotaoSalvar().addActionListener((g)->{
                exibe("c");
            });

            telaAtualizarDados.getBotaoVoltar().addActionListener((h)->{
                exibe("c");
            });

            telaMarcacaoAssento.getSalvar().addActionListener((i)->{
                exibe("a");
            });


        }
        return this.telaInicial;
    }

    private void exibe(String nome) {
        this.cardLayout.show(this.cardPanel, nome);
    }

}
