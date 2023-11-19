
package ControllerCliente;

import DB.DB_Cliente;
import DB.conexao_banco;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListModel;
import model.Cliente;
import views.extrato;

/**
 *
 * @author T-Gamer
 */
public class ControllerExtratoCliente {
    private extrato view;

    public ControllerExtratoCliente(extrato view) {
        this.view = view;
    }
    
    public void extrato(){
        String cpf = view.getEntrada_cpf().getText();
        String senha = view.getEntrada_senha().getText();
        
        conexao_banco conexao = new conexao_banco();
        try{
            Connection conn = conexao.getConnection();            
            DB_Cliente db = new DB_Cliente(conn);
            ResultSet res = db.getExtrato(cpf);
            JList<String> lista = this.view.getjList1();
            DefaultListModel<String> model = new DefaultListModel<String>();
            ListModel lModel = lista.getModel();
            
            Cliente cliente = db.getCliente(cpf,senha);
            // FAZER O IF PARA VERIFICACAO DO CLIENTE
            
            model.addElement(String.format("NOME: %s",cliente.getNome()));
            model.addElement(String.format("CPF: %s",cliente.getCpf()));
            model.addElement("CONTA: Corrente");
            model.addElement("");
            
            while(res.next()){
              String Data = String.format("Data: %17s    ", res.getString("data"));
              String tipo = String.format("    %s    ", res.getString("tipo"));
              String valor = String.format("    R$%10.2f    ", res.getDouble("valor"));
              String tarifa = String.format("    Tarifa: R$%10.2f    ", res.getDouble("tarifa"));
              String saldo = String.format("    Saldo: R$%10.2f", res.getDouble("saldo"));
              

              String clientFinalString = Data.concat(tipo).concat(valor).concat(tarifa).concat(saldo);
              model.addElement(clientFinalString);
            }
            lista.setModel(model);
            
            } catch (SQLException e){
                JOptionPane.showMessageDialog(view, "Cliente nao encontrado!", "Erro", JOptionPane.ERROR_MESSAGE); 
        }
    }
}
