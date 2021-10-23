package faeterj.prj.foxTurismo.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoComOBanco {

    private static Connection instancia = null;

    private ConexaoComOBanco() {}

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        if (instancia == null) {
            Class.forName("org.postgresql.Driver");
            instancia = DriverManager.getConnection("jdbc:postgresql://134.209.243.185/vavatur", "vavatur", "gGgLqu");
        }
        return instancia;
    }

}
