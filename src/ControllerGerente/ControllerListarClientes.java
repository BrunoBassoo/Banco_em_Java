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
            while(res.next()){
                String temp = "";
                for(int i = 0; i < 2; i++){
                    temp += res.getString(i + 1);
                    temp += "               ";
                } 
                for(int i = 3; i < 7; i++){
                    if(res.getString(i + 1) == null){
                        temp += "false";
                        temp += "               ";
                    }
                    else{
                        temp += res.getString(i + 1);
                        temp += "               ";
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
