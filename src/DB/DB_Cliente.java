package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Cliente;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    
    // usando o string sql = "'insert' 'into' cliente"
    // , foi feito para INSERIR tal informacao
    public void inserir(Cliente cliente)  throws SQLException{
        // vereifica se o CPF ja existe
        if(verificarExistenciaCliente(cliente.getCpf())){
            JOptionPane.showMessageDialog(null, "CPF já existente. Não foi possível criar a conta!", "Aviso",JOptionPane.ERROR_MESSAGE);

        }else{
            String sql = "insert into cliente (nome, cpf, senha, contaSalario, contaCorrente, contaPoupanca) values ('" + 
                cliente.getNome() + "' , '" + cliente.getCpf() + "' , '" + 
                cliente.getSenha() +  "' , '-10001' , '-10001' , '-10001')" ;
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.execute();
            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!", "Aviso",JOptionPane.INFORMATION_MESSAGE);
            conn.close();
        }
    }

    
    
    // usando o string sql = "'delete from' cliente 'where' cpf =?"
    // , foi feito para DELETAR o cliente
    public void remover(Cliente cliente) throws SQLException{
        // vereifica se o CPF ja existe
        if(verificarExistenciaCliente(cliente.getCpf())){
            String sql = "delete from cliente where cpf = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setString(1, cliente.getCpf());
            JOptionPane.showMessageDialog(null, "Cliente excluido com sucesso!", "Aviso",JOptionPane.INFORMATION_MESSAGE);            
            statement.execute();
            conn.close();
        } else{
            JOptionPane.showMessageDialog(null, "Cliente inexistente!", "Aviso", JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    
    public boolean verificarExistenciaCliente(String cpf) throws SQLException{
        String sql = "select * from cliente where cpf = ?";
        try(PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setString(1,cpf);
            try(ResultSet resultado = statement.executeQuery()){
                return resultado.next();
            }
        }
    }
    
    public boolean manusearCliente(String cpf, String senha) throws SQLException{
        String sql = "select * from cliente where cpf = ? and senha = ?";
        try(PreparedStatement statement = conn.prepareStatement(sql)){
            statement.setString(1,cpf);
            statement.setString(2,senha);
            try(ResultSet resultado = statement.executeQuery()){
                return resultado.next();
            }
        }
    }

    
    public Cliente getCliente(String cpf, String senha) throws SQLException{
        String sql = "select * from cliente where cpf = ? and senha = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, cpf);
        statement.setString(2, senha);
        statement.execute();
        ResultSet resultado = statement.getResultSet();
        resultado.next();
        if(senha.equals(resultado.getString("senha"))){
            return new Cliente( resultado.getString("nome"), resultado.getString("cpf"), resultado.getString("senha"), resultado.getDouble("contaSalario"), resultado.getDouble("contaCorrente"), resultado.getDouble("contaPoupanca"));
        }
        return new Cliente("", "", "");
    }
    
    
    public boolean updateCliente(Cliente cliente) throws SQLException{
        String sql = "update cliente set nome = ? , cpf = ? , senha = ? , contasalario = ? , contacorrente = ? , contapoupanca = ? where cpf = ? ";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, cliente.getNome());
        statement.setString(2, cliente.getCpf());
        statement.setString(3, cliente.getSenha());
        statement.setDouble(4, cliente.getCs());
        statement.setDouble(5, cliente.getCc());
        statement.setDouble(6, cliente.getCp()); 
        statement.setString(7, cliente.getCpf());
        statement.execute();
        conn.close();
        return true;
    }
    
    public Cliente getCliente(String cpf) throws SQLException{
        String sql = "select * from cliente where cpf = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, cpf);
        statement.execute();
        ResultSet resultado = statement.getResultSet();
        resultado.next();
        return new Cliente( resultado.getString("nome"), resultado.getString("cpf"), resultado.getString("senha"), resultado.getDouble("contaSalario"), resultado.getDouble("contaCorrente"), resultado.getDouble("contaPoupanca"));
    }
    
    public boolean salvarTransacao(Cliente cliente ,String tipo, double valor, double taxa) throws SQLException{
       String sql = "insert into transacoes (cpf, data, tipo, tarifa, valor, saldo) values ('" + 
                cliente.getCpf() + "' , '" + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")) + "' , '" + 
                tipo +  "' , '" + taxa + "' , '" + valor + "' , '" + cliente.getCc() + "')";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.execute();
        conn.close();
        return true;
    }
    
    public ResultSet getExtrato(String cpf) throws SQLException{
        String sql = "select * from transacoes where cpf = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1,cpf);
        statement.execute();
        ResultSet resultado = statement.getResultSet();
        return resultado;
    }
}

