package DB;

/**
 *
 * @author uniebrunosilva
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class conexao_banco {
    public Connection getConnection() throws SQLException{
        Connection conexao = DriverManager.getConnection(
        "jdbc:postgresql://localhost:5432/Java Bank",
        "postgres","fei");
        return conexao;
    
    }
}
