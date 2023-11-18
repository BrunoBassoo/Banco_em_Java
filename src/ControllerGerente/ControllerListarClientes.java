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
            System.out.println(conn.isValid(1));
            DB_Gerente db = new DB_Gerente(conn);
            ResultSet res = db.listarCliente();
            JList<String> lista = this.view.getjList();
            DefaultListModel<String> model = new DefaultListModel<String>();
            ListModel lModel = lista.getModel();
            String label1 = String.format("|%-21s|", "Nome:");
            String label2 = String.format("%-16s|", "CPF:");
            String label3 = String.format("%-16s|", "Saldo:");
            String label4 = String.format("%-9s|", "CS:");
            String label5 = String.format("%-9s|", "CC:");
            String label6 = String.format("%-9s|", "CP:");

            String finalLabel = label1.concat(label2).concat(label3).concat(label4).concat(label5).concat(label6);

            model.addElement(finalLabel);
            
            while(res.next()){
                String clientName;
              if(res.getString("nome").length() >= 20){
                  clientName = res.getString("nome").substring(0, 17) + "...";
                  clientName = String.format("|%-21s|", clientName);
                }
              else{
                  clientName = String.format("|%-21s|", res.getString("nome"));
              }
              String clientCPF = String.format("%16s|", res.getString("cpf"));
              String clientSalario = String.format("%16s|", res.getString("saldo"));
              String clientCS = String.format("%-9s|", res.getString("contaSalario"));
              String clientCC = String.format("%-9s|", res.getString("contaCorrente"));
              String clientCP = String.format("%-9s|", res.getString("contaPoupanca"));

              String clientFinalString = clientName.concat(clientCPF).concat(clientSalario).concat(clientCS).concat(clientCC).concat(clientCP);
              model.addElement(clientFinalString);
            }
            lista.setModel(model);
            
        } catch (SQLException e){
            JOptionPane.showMessageDialog(view, "Erro de conexão", "Erro", JOptionPane.ERROR_MESSAGE); 
    }
        
        
        
        
    
    
}
}
