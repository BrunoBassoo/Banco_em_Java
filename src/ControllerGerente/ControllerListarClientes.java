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
            model.addElement("|Nome:             |CPF:              |Saldo:              |Conta Salário:      |Conta Corrente:      |Conta Poupança:");
            while(res.next()){
                String temp = "";
                int chracteres = 21;
                int chracteres_rest;
                for(int i = 0; i < 2; i++){
                    if(res.getString(i + 1).length() < chracteres){
                        chracteres_rest = chracteres - (res.getString(i + 1).length());
                        System.out.println(chracteres_rest);
                        temp += res.getString(i + 1);
                        for(int j = 0; j < chracteres_rest; j++){
                            temp += " ";
                        }
                    }
                }
                for(int i = 3; i < 7; i++){
                    if(res.getString(i + 1) == null){
                        temp += "false";
                        temp += "              ";
                    }
                    else if(res.getString(i + 1).length() < chracteres){
                        chracteres_rest = chracteres - (res.getString(i + 1).length());
                        System.out.println(chracteres_rest);
                        temp += res.getString(i + 1);
                        for(int j = 0; j < chracteres_rest; j++){
                            temp += " ";
                        }
                        temp += res.getString(i + 1);
                    }
                }
                model.addElement(temp);
            }
            lista.setModel(model);
        } catch (SQLException e){
            JOptionPane.showMessageDialog(view, "Erro de conexão", "Erro", JOptionPane.ERROR_MESSAGE); 
    }
        
        
        
        
    
    
}
}
