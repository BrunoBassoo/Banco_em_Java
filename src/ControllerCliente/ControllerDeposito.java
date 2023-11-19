
package ControllerCliente;

import DB.DB_Cliente;
import DB.conexao_banco;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Cliente;
import views.Deposito;

/**
 *
 * @author T-Gamer
 */
public class ControllerDeposito { 
    private Deposito view;

    public ControllerDeposito(Deposito view) {
        this.view = view;
    }
    public void depositarSalario(){
        String cpf = view.getTxtCpfCliente().getText();
        String senha = view.getTxtSenhaCliente().getText();
        double valor_deposito = Double.parseDouble(view.getEntrada_valor().getText());

        conexao_banco conexao = new conexao_banco();
        try{
            Connection conn = conexao.getConnection();
            DB_Cliente db = new DB_Cliente(conn);
            Cliente res = db.getCliente(cpf,senha);
            if(res.getCpf().equals("") ){
                JOptionPane.showMessageDialog(null, "Cliente inexistente", "Aviso", JOptionPane.ERROR_MESSAGE);
            }
            else{
                res.DepositarContaSalario(valor_deposito);
                db.updateCliente(res);
                JOptionPane.showMessageDialog(view, "Deposito na conta salario efetuado com sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch( SQLException e){
            JOptionPane.showMessageDialog(view, "Erro de conexão, tente novamente!", "Aviso", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
        }
    }
    
    public void depositarPoupanca(){
        String cpf = view.getTxtCpfCliente().getText();
        String senha = view.getTxtSenhaCliente().getText();
        double valor_deposito = Double.parseDouble(view.getEntrada_valor().getText());

        conexao_banco conexao = new conexao_banco();
        try{
            Connection conn = conexao.getConnection();
            DB_Cliente db = new DB_Cliente(conn);
            Cliente res = db.getCliente(cpf,senha);
            if(res.getCpf().equals("") ){
                JOptionPane.showMessageDialog(null, "Cliente inexistente", "Aviso", JOptionPane.ERROR_MESSAGE);
            }
            else{
                res.DepositarContaPoupanca(valor_deposito);
                db.updateCliente(res);
                JOptionPane.showMessageDialog(view, "Deposito na conta poupanca efetuado com sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch( SQLException e){
            JOptionPane.showMessageDialog(view, "Erro de conexão, tente novamente!", "Aviso", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
        }
    }
    
    public void depositarCorrente(){
        String cpf = view.getTxtCpfCliente().getText();
        String senha = view.getTxtSenhaCliente().getText();
        double valor_deposito = Double.parseDouble(view.getEntrada_valor().getText());

        conexao_banco conexao = new conexao_banco();
        try{
            Connection conn = conexao.getConnection();
            DB_Cliente db = new DB_Cliente(conn);
            Cliente res = db.getCliente(cpf,senha);
            if(res.getCpf().equals("") ){
                JOptionPane.showMessageDialog(null, "Cliente inexistente", "Aviso", JOptionPane.ERROR_MESSAGE);
            }
            else{
                res.DepositarContaCorrente(valor_deposito);
                db.updateCliente(res);
                conn = conexao.getConnection();
                db = new DB_Cliente(conn);
                db.salvarTransacao(res,"+", valor_deposito, 0);
                JOptionPane.showMessageDialog(view, "Deposito na conta corrente efetuado com sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE);        
            }
        } catch( SQLException e){
            JOptionPane.showMessageDialog(view, "Erro de conexão, tente novamente!", "Aviso", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
        }
    }
}

