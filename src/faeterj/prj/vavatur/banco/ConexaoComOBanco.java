package faeterj.prj.vavatur.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoComOBanco {

    private static Connection instancia = null;

    private ConexaoComOBanco() {}

    public static Connection getConnection() throws SQLException {
        if (instancia == null) {
            instancia = DriverManager.getConnection("jdbc:postgresql://localhost:5432/prj", "postgres", "postgres");
        }
        return instancia;
    }

}
