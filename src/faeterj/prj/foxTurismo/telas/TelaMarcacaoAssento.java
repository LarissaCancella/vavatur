package faeterj.prj.foxTurismo.telas;

import faeterj.prj.foxTurismo.banco.RepositorioDados;
import faeterj.prj.foxTurismo.negocio.Bilhete;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import javax.swing.*;

public class TelaMarcacaoAssento extends JPanel {

    private JLabel info;
    private JButton salvar = new JButton("Salvar");
    private PainelDeMarcacaoDeAssentos painelDeMarcacaoDeAssentos = new PainelDeMarcacaoDeAssentos();
    private Bilhete bilhete;
    private RepositorioDados repositorioDados = RepositorioDados.getInstancia();
    private TelaBilhete telaBilhete;

    public TelaMarcacaoAssento() {
        setLayout(new BorderLayout(10, 10));
        this.setBackground((Color.decode("#EFC2B0")));

        painelDeMarcacaoDeAssentos.PainelDeMarcacaoDeAssento(48);

        add(painelDeMarcacaoDeAssentos, BorderLayout.CENTER);

        if(salvar.isEnabled()) {
            salvar.addActionListener((e) -> {
                try {
                    List<Integer> listaAssentos = repositorioDados.buscarAssentosPorLinha(bilhete);
                    for(Integer assento : listaAssentos) {
                        painelDeMarcacaoDeAssentos.setAssento(assento, Situacao.OCUPADO);
                    }
                    //String assentoOcupado = bilhete.getAssento();
                    bilhete.setAssento(String.valueOf(painelDeMarcacaoDeAssentos.getAssentoSelecionado()));
                    bilhete.setHoraMarcadaAssento(LocalDateTime.now());
                    repositorioDados.salvarAssento(bilhete);
                    telaBilhete.getCampoBilhete().setText("");
                    //painelDeMarcacaoDeAssentos.setAssento(Integer.parseInt(assentoOcupado), Situacao.LIVRE);
                    JOptionPane.showMessageDialog(null, "Marcado assento numero " + painelDeMarcacaoDeAssentos.getAssentoSelecionado());
                    JOptionPane.showMessageDialog(null, "Check-in completo");
                } catch (SQLException | ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Não foi possível salvar o assento");
                }
            });
        }

        add(salvar, BorderLayout.SOUTH);

        info = new JLabel("Selecione o seu assento (Assentos em vermelho ja foram selecionados)");
        info.setFont(new Font("Serif", Font.PLAIN, 25));
        add(info, BorderLayout.NORTH);

    }

    public void setAssentoOcupado() throws SQLException, ClassNotFoundException {
        List<Integer> listaAssentos = repositorioDados.buscarAssentosPorLinha(bilhete);
        for(Integer assento : listaAssentos) {
            painelDeMarcacaoDeAssentos.setAssento(assento, Situacao.OCUPADO);
        }
        painelDeMarcacaoDeAssentos.setAssento(Integer.parseInt(bilhete.getAssento()), Situacao.OCUPADO);
    }

    public void setBilhete(Bilhete bilhete) {this.bilhete = bilhete;}
    public JButton getSalvar() {return salvar;}
    public void setTelaBilhete(TelaBilhete telaBilhete) {this.telaBilhete = telaBilhete;}
}
