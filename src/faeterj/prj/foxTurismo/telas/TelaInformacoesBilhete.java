package faeterj.prj.foxTurismo.telas;

import faeterj.prj.foxTurismo.negocio.Bilhete;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class TelaInformacoesBilhete extends JPanel {

    private JLabel infos;
    private JLabel infoGeral;
    private JPanel cardPanel;
    private JPanel telaInformacoesBilhete;
    private CardLayout cardLayout;
    private Bilhete bilhete;
    private TelaAtualizarDados telaAtualizarDados;
    private TelaMarcacaoAssento telaMarcacaoAssento;
    private JButton botaoProximo = new JButton("Proximo");
    private JButton botaoAttDados = new JButton("Atualizar dados");
    private LocalDateTime horaRelogio = LocalDateTime.now();

    public TelaInformacoesBilhete() {
        this.cardPanel = new JPanel();
        this.cardLayout = new CardLayout();
        this.cardPanel.setLayout(this.cardLayout);
        this.setBackground((Color.decode("#EFC2B0")));
        add(getTelaInformacoesBilhete());
    }

    private JPanel getTelaInformacoesBilhete() {
        if (this.telaInformacoesBilhete == null) {
            this.telaInformacoesBilhete = new JPanel();
            this.telaInformacoesBilhete.setLayout(new GridLayout(1,1));
            this.telaInformacoesBilhete.setBorder(new EmptyBorder(new Insets(3, 3, 3, 3)));
            this.telaInformacoesBilhete.setBackground(Color.decode("#EFC2B0"));

        }
        return this.telaInformacoesBilhete;
    }

    public void setLabelInformacoes() {
        infos = new JLabel("Informações do Bilhete");
        infos.setFont(new Font("Serif", Font.PLAIN, 25));
        add(infos,BorderLayout.LINE_START);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        String horaRelogioFormatada = horaRelogio.format(formatter);


        infoGeral = new JLabel("<html><body>Origem<br>" + bilhete.getLinha().getOrigem() +
                "<br><br>Destino<br>" + bilhete.getLinha().getDestino() +
                "<br><br>Hora que sera efetuado o embarque do passageiro. (Atrasos não serão tolerados!)<br>" + bilhete.getLinha().getHoraEmbarque() +
                "<br><br>Hora da partida<br>" + bilhete.getLinha().getHoraPartida() +
                "<br><br>Hora atual<br>" + horaRelogioFormatada +
                "<br><br>Nome<br>" + bilhete.getPassageiro().getNome() +
                "<br><br>CPF<br>" + bilhete.getPassageiro().getCpf() + "</html></body>" );
        infoGeral.setFont(new Font("Serif", Font.PLAIN, 20));
        add(infoGeral,BorderLayout.CENTER);

        add(botaoProximo,BorderLayout.PAGE_END);
        botaoProximo.addActionListener((e) -> {
            telaMarcacaoAssento.setBilhete(bilhete);
            try {
                telaMarcacaoAssento.setAssentoOcupado();
            } catch (SQLException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        });
        add(botaoAttDados,BorderLayout.PAGE_END);
        botaoAttDados.addActionListener((e) -> {
            telaAtualizarDados.getCampoNome().setText("");
            telaAtualizarDados.getCampoCPF().setText("");
            telaAtualizarDados.setBilhete(bilhete);
        });
    }

    public void setBilhete(Bilhete bilhete) {this.bilhete = bilhete;}
    public JButton getBotaoProximo() {return botaoProximo;}
    public JButton getBotaoAttDados() {return botaoAttDados;}
    public void setTelaAtualizarDados(TelaAtualizarDados telaAtualizarDados) {this.telaAtualizarDados = telaAtualizarDados;}
    public void setTelaMarcacaoAssento(TelaMarcacaoAssento telaMarcacaoAssento) {this.telaMarcacaoAssento = telaMarcacaoAssento;}
}
