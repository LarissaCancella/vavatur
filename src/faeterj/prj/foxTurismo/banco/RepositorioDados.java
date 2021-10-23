package faeterj.prj.foxTurismo.banco;

import faeterj.prj.foxTurismo.negocio.Bilhete;
import faeterj.prj.foxTurismo.negocio.Linha;
import faeterj.prj.foxTurismo.negocio.Passageiro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class RepositorioDados {

    private RepositorioDados() {}

    private static final RepositorioDados instancia = new RepositorioDados();

    public static RepositorioDados getInstancia() {
        return instancia;
    }

    public Bilhete buscarBilhetePorCodigo(String codigo) throws SQLException, ClassNotFoundException {
        Connection c = ConexaoComOBanco.getConnection();
        PreparedStatement cmd = c.prepareStatement("select * from bilhete where codigo = ?");
        cmd.setString(1, codigo);
        cmd.execute();

        Bilhete bilhete = new Bilhete();
        Passageiro passageiro = new Passageiro();
        Linha linha = new Linha();

        try (ResultSet rs = cmd.getResultSet()) {
            if (rs.next()) {
                bilhete.setCodigo(rs.getString(1));
                bilhete.setAssento(rs.getString(2));
                PreparedStatement passageiros = c.prepareStatement("select * from passageiro where id = ?");
                passageiros.setInt(1, rs.getInt(3));
                passageiros.execute();
                try (ResultSet rsPassageiros = passageiros.getResultSet()) {
                    if (rsPassageiros.next()) {
                        passageiro.setId(rsPassageiros.getInt(1));
                        passageiro.setNome(rsPassageiros.getString(2));
                        passageiro.setCpf(rsPassageiros.getString(3));
                        passageiro.setTelefone(rsPassageiros.getString(4));
                        passageiro.setEmail(rsPassageiros.getString(3));
                    }
                }
                PreparedStatement linhas = c.prepareStatement("select * from linha where id = ?");
                linhas.setInt(1, rs.getInt(4));
                linhas.execute();
                try (ResultSet rsLinhas = linhas.getResultSet()) {
                    if (rsLinhas.next()) {
                        linha.setId(rsLinhas.getInt(1));
                        linha.setOrigem(rsLinhas.getString(2));
                        linha.setDestino(rsLinhas.getString(3));
                        linha.setHoraEmbarque(rsLinhas.getObject(4, LocalTime.class));
                        linha.setHoraPartida(rsLinhas.getObject(5, LocalTime.class));
                    }
                }
                bilhete.setPassageiro(passageiro);
                bilhete.setLinha(linha);
                bilhete.setHoraMarcadaAssento(rs.getObject(5, LocalDateTime.class));
                return bilhete;
            }
        }
        throw new RuntimeException("Bilhete com código " + codigo + " não encontrado!");
    }

    public void atualizarPassageiro(Passageiro passageiro) throws SQLException, ClassNotFoundException {
        Connection c = ConexaoComOBanco.getConnection();
        PreparedStatement cmd = c.prepareStatement("update passageiro set nome = ?, cpf = ? where id = ?");
        cmd.setString(1, passageiro.getNome());
        cmd.setString(2, passageiro.getCpf());
        cmd.setInt(3, passageiro.getId());
        cmd.execute();
    }

    public List<Integer> buscarAssentosPorLinha(Bilhete bilhete) throws SQLException, ClassNotFoundException {
        Connection c = ConexaoComOBanco.getConnection();
        PreparedStatement cmd = c.prepareStatement("select assento from bilhete where id_linha = ?");
        cmd.setInt(1, bilhete.getLinha().getId());
        cmd.execute();

        List<Integer> listaAssentos = new ArrayList<>();

        try (ResultSet rsLinhas = cmd.getResultSet()) {
            while (rsLinhas.next()) {
                if(rsLinhas.getString(1) != null) {
                    listaAssentos.add(Integer.valueOf(rsLinhas.getString(1)));
                }
            }
        }

        return listaAssentos;
    };

    public Passageiro buscarPassageiro(int id) throws SQLException, ClassNotFoundException {
        Connection c = ConexaoComOBanco.getConnection();
        PreparedStatement cmd = c.prepareStatement("select * from passageiro where id = ?");
        cmd.setInt(1, id);
        cmd.execute();

        Passageiro passageiro = new Passageiro();

        try (ResultSet rsPassageiros = cmd.getResultSet()) {
            if (rsPassageiros.next()) {
                passageiro.setId(rsPassageiros.getInt(1));
                passageiro.setNome(rsPassageiros.getString(2));
                passageiro.setCpf(rsPassageiros.getString(3));
                passageiro.setTelefone(rsPassageiros.getString(4));
                passageiro.setEmail(rsPassageiros.getString(3));
            }
        }

        return passageiro;
    }

    public void salvarAssento(Bilhete bilhete) throws SQLException, ClassNotFoundException {
        Connection c = ConexaoComOBanco.getConnection();
        PreparedStatement cmd = c.prepareStatement("update bilhete set assento = ?, assento_marcado_em = ? where codigo = ?");
        cmd.setInt(1, Integer.parseInt(bilhete.getAssento()));
        cmd.setObject(2, bilhete.getHoraMarcadaAssento());
        cmd.setString(3, bilhete.getCodigo());
        cmd.execute();
    }

}
