package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Gerente;

/**
 *
 * @author uniebrunosilva
 */
public class DB_Gerente {
    
    private Connection conn;

    public DB_Gerente(Connection conn) {
        this.conn = conn;
    }
    
    // consultar gerente
    
    
    
    
    
    public ResultSet consultarGerente (Gerente gerente) throws SQLException{
        // usando o string sql = "'select *from' cliente 'where' cpf = ? and senha = ?"
        // foi feito para SELECIONAR tal informacao
        String sql = "select * from gerente where cpf = ? and senha = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, gerente.getCpf());
        statement.setString(2, gerente.getSenha());
        statement.execute();
        ResultSet resultado = statement.getResultSet();
        return resultado;
    }
}