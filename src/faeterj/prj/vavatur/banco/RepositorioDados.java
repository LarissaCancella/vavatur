package faeterj.prj.vavatur.banco;

import faeterj.prj.vavatur.negocio.Bilhete;
import faeterj.prj.vavatur.negocio.Linha;
import faeterj.prj.vavatur.negocio.Passageiro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class RepositorioDados {

    private RepositorioDados() {}

    private static RepositorioDados instancia = new RepositorioDados();

    public static RepositorioDados getInstancia() {
        return instancia;
    }

    public Bilhete buscarBilhetePorCodigo(String codigo) throws SQLException {
        Connection c = ConexaoComOBanco.getConnection();
        PreparedStatement cmd = c.prepareStatement("select codigo, assento, * from bilhete where id = ?");
        cmd.setString(1, codigo);
        cmd.execute();

        try (ResultSet rs = cmd.getResultSet()) {
            if (rs.next()) {
                Bilhete b = new Bilhete();
                b.setCodigo(rs.getString(1));
                // ...
                return b;
            }
        }
        throw new RuntimeException("Bilhete com código " + codigo + " não encontrado!");
    }

    public void salvarBilhete(Bilhete bilhete) {

    }

    public List<Passageiro> listarPassageirosLinha(Linha linha) {
        return null;
    }

    public static RepositorioDados getInstance() {
        return instancia;
    }

}
