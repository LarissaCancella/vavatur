package faeterj.prj.vavatur.banco;

import faeterj.prj.vavatur.negocio.Bilhete;
import faeterj.prj.vavatur.negocio.Linha;
import faeterj.prj.vavatur.negocio.Passageiro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RepositorioDados {

    private RepositorioDados() {}

    private static final RepositorioDados instancia = new RepositorioDados();

    public static RepositorioDados getInstancia() {
        return instancia;
    }

    public Bilhete buscarBilhetePorCodigo(String codigo) throws SQLException {
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
                cmd.setString(1, rs.getString(3));
                cmd.execute();
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
                cmd.setString(1, rs.getString(4));
                cmd.execute();
                try (ResultSet rsLinhas = linhas.getResultSet()) {
                    if (rsLinhas.next()) {
                        linha.setId(rsLinhas.getInt(1));
                        linha.setOrigem(rsLinhas.getString(2));
                        linha.setDestino(rsLinhas.getString(2));
                        //linha.setHoraEmbarque(rsLinhas.getString(3));
                        //linha.setHoraPartida(rsLinhas.getString(4));
                    }
                }
                bilhete.setPassageiro(passageiro);
                bilhete.setLinha(linha);
                //bilhete.setHoraMarcadaAssento(null);
                return bilhete;
            }
        }
        throw new RuntimeException("Bilhete com código " + codigo + " não encontrado!");
    }

    public void salvarBilhete(Bilhete bilhete) throws SQLException {
        Connection c = ConexaoComOBanco.getConnection();
        PreparedStatement cmd = c.prepareStatement("insert into bilhete values(?,?,?,?,?)");
        cmd.setString(1, bilhete.getCodigo());
        cmd.setString(2, bilhete.getAssento());
        cmd.setInt(3, bilhete.getPassageiro().getId());
        cmd.setInt(4, bilhete.getLinha().getId());
        //cmd.setDate(5, bilhete.getHoraMarcadaAssento());
        cmd.execute();
    }

    public void atualizarPassageiro(Passageiro passageiro) throws SQLException {
        Connection c = ConexaoComOBanco.getConnection();
        PreparedStatement cmd = c.prepareStatement("update passageiro set nome = ?, cpf = ? where id = ?");
        cmd.setString(1, passageiro.getNome());
        cmd.setString(2, passageiro.getCpf());
        cmd.setInt(3, passageiro.getId());
        cmd.execute();
    }

    public void salvarAssento(Bilhete bilhete) throws SQLException {
        Connection c = ConexaoComOBanco.getConnection();
        PreparedStatement cmd = c.prepareStatement("update bilhete set assento = ? where codigo = ?");
        cmd.setString(1, bilhete.getAssento());
        cmd.setString(2, bilhete.getCodigo());
        cmd.execute();
    }

}
