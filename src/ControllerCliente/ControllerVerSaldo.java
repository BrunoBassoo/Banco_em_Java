package ControllerCliente;

import DB.DB_Cliente;
import DB.conexao_banco;
import views.ExibirSaldoCliente;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import model.Cliente;

/**
 *
 * @author T-Gamer
 */

public class ControllerVerSaldo {
    private ExibirSaldoCliente view;

    public ControllerVerSaldo(ExibirSaldoCliente view) {
        this.view = view;
    }
    
    public void saldoSalario(){
        String cpf = view.getEntrada_cpf().getText();
        String senha = view.getEntrada_senha().getText();
        
        conexao_banco conexao = new conexao_banco();
        try{
            Connection conn = conexao.getConnection();
            DB_Cliente db = new DB_Cliente(conn);
            Cliente res = db.getCliente(cpf, senha);
            if(res.getCpf().equals("")){
                JOptionPane.showMessageDialog(null, "Cliente inexistente", "Aviso", JOptionPane.ERROR_MESSAGE);                
            }
            else{
                boolean verificar_conta = res.hasContaSalario();
                if(verificar_conta){
                    JOptionPane.showMessageDialog(null,String.format("Saldo da conta salario: R$ %.2f", res.getCs()), "Aviso", JOptionPane.INFORMATION_MESSAGE);   
                }
                else{
                   JOptionPane.showMessageDialog(null,"Conta inexistente!", "Aviso", JOptionPane.ERROR_MESSAGE); 
                }
            }
   
        } catch (SQLException e){
            JOptionPane.showMessageDialog(view, "Erro de conexão", "Erro", JOptionPane.ERROR_MESSAGE); 

        }
    }
    
    public void saldoCorrente(){
        String cpf = view.getEntrada_cpf().getText();
        String senha = view.getEntrada_senha().getText();
        
        conexao_banco conexao = new conexao_banco();
        try{
            Connection conn = conexao.getConnection();
            DB_Cliente db = new DB_Cliente(conn);
            Cliente res = db.getCliente(cpf, senha);
            if(res.getCpf().equals("")){
                JOptionPane.showMessageDialog(null, "Cliente inexistente", "Aviso", JOptionPane.ERROR_MESSAGE);                
            }
            else{
                boolean verificar_conta = res.hasContaCorrente();
                if(verificar_conta){
                    JOptionPane.showMessageDialog(null,String.format("Saldo da conta corrente: R$ %.2f", res.getCc()), "Aviso", JOptionPane.INFORMATION_MESSAGE);   
                }
                else{
                   JOptionPane.showMessageDialog(null,"Conta inexistente!", "Aviso", JOptionPane.ERROR_MESSAGE); 
                }
            }
   
        } catch (SQLException e){
            JOptionPane.showMessageDialog(view, "Erro de conexão", "Erro", JOptionPane.ERROR_MESSAGE); 

        }
    }
    
    public void saldoPoupanca(){
        String cpf = view.getEntrada_cpf().getText();
        String senha = view.getEntrada_senha().getText();
        
        conexao_banco conexao = new conexao_banco();
        try{
            Connection conn = conexao.getConnection();
            DB_Cliente db = new DB_Cliente(conn);
            Cliente res = db.getCliente(cpf, senha);
            if(res.getCpf().equals("")){
                JOptionPane.showMessageDialog(null, "Cliente inexistente", "Aviso", JOptionPane.ERROR_MESSAGE);                
            }
            else{
                boolean verificar_conta = res.hasContaPoupanca();
                if(verificar_conta){
                    JOptionPane.showMessageDialog(null,String.format("Saldo da conta poupanca: R$ %.2f", res.getCp()), "Aviso", JOptionPane.INFORMATION_MESSAGE);   
                }
                else{
                   JOptionPane.showMessageDialog(null,"Conta inexistente!", "Aviso", JOptionPane.ERROR_MESSAGE); 
                }
            }
   
        } catch (SQLException e){
            JOptionPane.showMessageDialog(view, "Erro de conexão", "Erro", JOptionPane.ERROR_MESSAGE); 

        }
    }
    
}
