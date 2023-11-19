package ControllerGerente;

import DB.DB_Cliente;
import DB.conexao_banco;
import views.ExibirSaldoTotal;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Cliente;

/**
 *
 * @author T-Gamer
 */
public class ControllerExibirSaldos {
    private ExibirSaldoTotal view;

    public ControllerExibirSaldos(ExibirSaldoTotal view) {
        this.view = view;
    }
    
    
    public void saldos(){
        String cpf = view.getEntrada_cpf().getText();
        conexao_banco conexao = new conexao_banco();
        try{
            Connection conn = conexao.getConnection();
            DB_Cliente db = new DB_Cliente(conn);
            Cliente res = db.getCliente(cpf);
            if(res.getCpf().equals("")){
                JOptionPane.showMessageDialog(null, "Cliente inexistente", "Aviso", JOptionPane.ERROR_MESSAGE);                
            }
            else{
                if(res.getCs() > -10001){
                    this.view.getLbSalario().setText(String.format("R$ %.2f",res.getCs()));
                }
                else 
                {
                    this.view.getLbSalario().setText("Conta inexistente!");
                }
                if(res.getCc() > -10001){
                    this.view.getLbCorrente().setText(String.format("R$ %.2f", res.getCc()));                    
                }
                else
                {
                    this.view.getLbCorrente().setText("Conta inexistente!");
                }
                if(res.getCp() > -10001){
                    this.view.getLbPoupanca().setText(String.format("R$ %.2f", res.getCp()));
                }
                else
                {
                    this.view.getLbPoupanca().setText("Conta inexistente!");
                }
                
                    
            }
        } catch (SQLException e){
            JOptionPane.showMessageDialog(view, "Cliente nao encontrado, tente novamente!", "Aviso", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
