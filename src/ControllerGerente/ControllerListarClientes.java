package ControllerGerente;

import DB.DB_Gerente;
import DB.conexao_banco;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import views.ListarClientela;
import javax.swing.JList;
import javax.swing.ListModel;
import javax.swing.DefaultListModel;

/**
 *
 * @author T-Gamer
 */
public class ControllerListarClientes {
    private ListarClientela view;
    
    public ControllerListarClientes(ListarClientela view) {
        this.view = view;
    }
    
    
    public void listar(){
        conexao_banco conexao = new conexao_banco();
        try{
            
            Connection conn = conexao.getConnection();
            DB_Gerente db = new DB_Gerente(conn);
            ResultSet res = db.listarCliente();
            JList<String> lista = this.view.getjList();
            DefaultListModel<String> model = new DefaultListModel<String>();
            ListModel lModel = lista.getModel();
            String label1 = String.format("|%-21s|", "Nome:");
            String label2 = String.format("%-16s |", "CPF:");
            String label4 = String.format("%-17s |", " Conta Salario: ");
            String label5 = String.format("%-16s |", " Conta Corrente: ");
            String label6 = String.format("%-16s |", " Conta Poupanca: ");
            String clientCS;
            String clientCC;
            String clientCP;
            String finalLabel = label1.concat(label2).concat(label4).concat(label5).concat(label6);

            model.addElement(finalLabel);
            
            while(res.next()){
                String clientName;
                
              if(res.getString("nome").length() >= 20){
                  clientName = res.getString("nome").substring(0, 17) + "...";
                  clientName = String.format(" %-21s|", clientName);
                }
              else{
                  clientName = String.format(" %-21s|", res.getString("nome"));
              }
              String clientCPF = String.format(" %-16s|", res.getString("cpf"));
              if(res.getDouble("contasalario") < -10000){
                clientCS = String.format(" %-17s|", "inexistente!");
              }
              else
              {
                clientCS = String.format(" R$ %-14s|", res.getDouble("contasalario"));                  
              }
              if(res.getDouble("contacorrente") < -10000){
                clientCC = String.format(" %-17s|", "inexistente!");
              } 
              else
              {
              clientCC = String.format(" R$ %-14s|", res.getDouble("contacorrente"));
                  
              }
              if(res.getDouble("contapoupanca") < -10000){
                clientCP = String.format(" %-17s|", "inexistente!");
              } 
              else
              {
              clientCP = String.format(" R$ %-14s|", res.getDouble("contapoupanca"));                  
              }
              String clientFinalString = clientName.concat(clientCPF).concat(clientCS).concat(clientCC).concat(clientCP);
              model.addElement(clientFinalString);
            }
            lista.setModel(model);
            
        } catch (SQLException e){
            JOptionPane.showMessageDialog(view, "Cliente nao encontrado!", "Erro", JOptionPane.ERROR_MESSAGE); 
    }
        
        
        
        
    
    
}
}
