package ControllerCliente;

import DB.DB_Cliente;
import DB.conexao_banco;
import model.Cliente;
import views.CriarConta;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;


/**
 *
 * @author ZAKAGORILAO
 */
public class ControllerCriarConta {
    private CriarConta view;

    public ControllerCriarConta(CriarConta view) {
        this.view = view;
    }
    
    public void CriarContaCorrente(){
        
        String cpf = view.getEntrada_cpf().getText();
        String senha = view.getEntrada_senha().getText();
        double valor = Double.parseDouble(view.getEntrada_valor().getText());
        
        conexao_banco conexao = new conexao_banco();
        try{
            Connection conn = conexao.getConnection();
            DB_Cliente db = new DB_Cliente(conn);
            Cliente res = db.getCliente(cpf,senha);
            if(res.getCpf().equals("") ){
                JOptionPane.showMessageDialog(null, "Cliente inexistente", "Aviso", JOptionPane.ERROR_MESSAGE);
            }
            else{
                if(res.hasContaCorrente()){
                    JOptionPane.showMessageDialog(null,"Esta conta ja existe!", "Aviso",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    res.addContaCorrente(valor);
                    db.updateCliente(res);
                    JOptionPane.showMessageDialog(null,"Conta Corrente criada com sucesso e banco de dados atualizado!!", "Aviso",JOptionPane.INFORMATION_MESSAGE);
                    
                }
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro de conexão!", "Aviso", JOptionPane.ERROR_MESSAGE);
            
            e.printStackTrace();
        }
    }
    
    public void CriarContaPoupanca(){
        
        String cpf = view.getEntrada_cpf().getText();
        String senha = view.getEntrada_senha().getText();
        double valor = Double.parseDouble(view.getEntrada_valor().getText());
        
        conexao_banco conexao = new conexao_banco();
        try{
            Connection conn = conexao.getConnection();
            DB_Cliente db = new DB_Cliente(conn);
            Cliente res = db.getCliente(cpf,senha);
            if(res.getNome().equals("") ){
                JOptionPane.showMessageDialog(null, "Cliente inexistente", "Aviso", JOptionPane.ERROR_MESSAGE);
            }
            else{
                if(res.hasContaPoupanca()){
                    JOptionPane.showMessageDialog(null,"Esta conta ja existe!", "Aviso",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    res.addContaPoupanca(valor);
                    db.updateCliente(res);
                    JOptionPane.showMessageDialog(null,"Conta Poupanca criada com sucesso e banco de dados atualizado!!", "Aviso",JOptionPane.INFORMATION_MESSAGE);
                    
                }
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro de conexão!", "Aviso", JOptionPane.ERROR_MESSAGE);
            
            e.printStackTrace();
        }
    }
    
    public void CriarContaSalario(){
        
        String cpf = view.getEntrada_cpf().getText();
        String senha = view.getEntrada_senha().getText();
        double valor = Double.parseDouble(view.getEntrada_valor().getText());
        
        conexao_banco conexao = new conexao_banco();
        try{
            Connection conn = conexao.getConnection();
            DB_Cliente db = new DB_Cliente(conn);
            Cliente res = db.getCliente(cpf,senha);
            if(res.getNome().equals("") ){
                JOptionPane.showMessageDialog(null, "Cliente inexistente", "Aviso", JOptionPane.ERROR_MESSAGE);
            }
            else{
                if(res.hasContaSalario()){
                    JOptionPane.showMessageDialog(null,"Esta conta ja existe!", "Aviso",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    res.addContaSalario(valor);
                    db.updateCliente(res);
                    JOptionPane.showMessageDialog(null,"Conta Salario criada com sucesso e banco de dados atualizado!!", "Aviso",JOptionPane.INFORMATION_MESSAGE);
                    
                }
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro de conexão!", "Aviso", JOptionPane.ERROR_MESSAGE);
            
            e.printStackTrace();
        }
    }
}
