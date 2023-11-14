package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Cliente;
import model.Gerente;

/**
 *
 * @author uniebrunosilva
 */
public class DB_Cliente {
    
    private Connection conn;

    public DB_Cliente(Connection conn) {
        this.conn = conn;
    }
    
    public ResultSet consultarCliente (Cliente cliente) throws SQLException{
        // usando o string sql = "'select *from' cliente 'where' cpf = ? and senha = ?"
        // foi feito para SELECIONAR tal informacao
        String sql = "select * from cliente where cpf = ? and senha = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, cliente.getCpf());
        statement.setString(2, cliente.getSenha());
        statement.execute();
        ResultSet resultado = statement.getResultSet();
        return resultado;
    }
    
    public ResultSet VerificacaoCliente (Cliente cliente) throws SQLException{
        // usando o string sql = "'select *from' cliente 'where' cpf = ? and senha = ?"
        // foi feito para SELECIONAR tal informacao
        String sql = "select * from cliente where cpf = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, cliente.getCpf());
        statement.execute();
        ResultSet resultado = statement.getResultSet();
        return resultado;
    }
    // usando o string sql = "'insert' 'into'cliente"
    // , foi feito para INSERIR tal informacao
    public void inserir(Cliente cliente)  throws SQLException{
        String sql = "insert into cliente (nome, cpf,senha, saldo) values ('" + 
                cliente.getNome() + "' , '" + cliente.getCpf() + "' , '" + 
                cliente.getSenha() + "' , '" + cliente.getSaldo() + "')" ;
        
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.execute();
        
        conn.close();
    }
    
    // usando o string sql = "'update' cliente 'set' senha = ?'where' cpf = ?"
    // , foi feito para ATUALIZAR/ o saldo
    
    //DUVIDA DO DEBITAR, ESTA DANDO ERRO DE CONEXAO!
    public void debitar(Cliente cliente,double valor_deb) throws SQLException{
        String sql = "update cliente set saldo = ? where cpf = ? and senha = ?";
        PreparedStatement statement = conn.prepareStatement(sql); 
        System.out.println(cliente.getSaldo());
        statement.setDouble(1, cliente.getSaldo() - valor_deb); 
        // for para percorrer o banco de dados e achar o cpf
        // equivalente ao cpf e a senha na qual o cliente digitou.
        statement.setString(2,cliente.getCpf());
        statement.setString(3,cliente.getSenha());
        statement.execute();
        conn.close();
    }
    
    // está errado tambem, tanto como debitar (nao sei como ajeitar isso)
    public void deposito(Cliente cliente,double valor_dep) throws SQLException{
        String sql = "update cliente set saldo = ? where cpf and senha = ?";
        PreparedStatement statement = conn.prepareStatement(sql); 
        // for para percorrer o banco de dados e achar o cpf
        // equivalente ao cpf e a senha na qual o cliente digitou.
        statement.setDouble(1, cliente.getSaldo() + valor_dep); // erro aq
        statement.setString(2,cliente.getCpf());
        statement.setString(3,cliente.getSenha());
        statement.execute();
        conn.close();
    }
    
    // usando o string sql = "'delete from' cliente 'where' cpf =?"
    // , foi feito para DELETAR o cliente
    public void remover(Cliente cliente) throws SQLException{
        String sql = "delete from cliente where cpf = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, cliente.getCpf());
        statement.execute();
        conn.close();
        
    }
    
    // GERENTE
    public ResultSet consultar (Gerente gerente) throws SQLException{
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
