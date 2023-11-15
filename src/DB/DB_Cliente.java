package DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
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
    
    // usando o string sql = "'insert' 'into' cliente"
    // , foi feito para INSERIR tal informacao
    public void inserir(Cliente cliente)  throws SQLException{
        // vereifica se o CPF ja existe
        if(verificarExistenciaCliente(cliente.getCpf())){
            JOptionPane.showMessageDialog(null, "CPF já existente. Não foi possível criar a conta!", "Aviso",JOptionPane.ERROR_MESSAGE);

        }else{
            String sql = "insert into cliente (nome, cpf,senha, saldo) values ('" + 
                cliente.getNome() + "' , '" + cliente.getCpf() + "' , '" + 
                cliente.getSenha() + "' , '" + cliente.getSaldo() + "')" ;
                JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!", "Aviso",JOptionPane.INFORMATION_MESSAGE);
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.execute();
                conn.close();
        }
    }
    
    // usando o string sql = "'update' cliente 'set' senha = ?'where' cpf = ?"
    // , foi feito para ATUALIZAR/ o saldo
    
    //DUVIDA DO DEBITAR, ESTA DANDO ERRO DE CONEXAO!
    public void debitar(Cliente cliente,double valor_deb) throws SQLException{
        if(manusearCliente(cliente.getCpf(),cliente.getSenha())){
            String sql = "update cliente set saldo = ? where cpf = ? and senha = ?";
            PreparedStatement statement = conn.prepareStatement(sql); 
            double valor_total = cliente.getSaldo() - valor_deb;
            cliente.setSaldo(valor_total);
            statement.setDouble(1, cliente.getSaldo()); 
            statement.setString(2,cliente.getCpf());
            statement.setString(3,cliente.getSenha());
            JOptionPane.showMessageDialog(null,"Débito Efetuado com sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            statement.execute();
            conn.close();
        } else{
            JOptionPane.showMessageDialog(null, "Cliente inexistente, tente novamente!", "Aviso",  JOptionPane.ERROR_MESSAGE);
        }
        
    }
    
    // está errado tambem, tanto como debitar (nao sei como ajeitar isso)
    public void deposito(Cliente cliente,double valor_dep) throws SQLException{
        if(manusearCliente(cliente.getCpf(),cliente.getSenha())){
            String sql = "update cliente set saldo = ? where cpf = ? and senha = ?";
            double valor_total = cliente.getSaldo() + valor_dep;
            cliente.setSaldo(valor_total);
            PreparedStatement statement = conn.prepareStatement(sql); 
            statement.setDouble(1, cliente.getSaldo());    
            statement.setString(2,cliente.getCpf());            
            statement.setString(3,cliente.getSenha());
            JOptionPane.showMessageDialog(null,"Depósito Efetuado com sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            statement.execute();
            conn.close();
        }else{
            JOptionPane.showMessageDialog(null, "Cliente inexistente, tente novamente!", "Aviso",  JOptionPane.ERROR_MESSAGE);
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
}
