package ControllerGerente;

import DB.DB_Gerente;
import DB.conexao_banco;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import javax.swing.JTable;
import views.ListarClientela;

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
            int c = 1;
            //JTable tabela = view.getJTable();
            while(res.next()){
                
                System.out.println("------------");
                System.out.println("Cliente " + c);
                for(int i = 0; i < 2; i++){
                    //tableModel.insertRow(res.getString(i + 1));
                } 
                for(int i = 3; i < 7; i++){
                   // tableModel.insertRow(res.getString(i + 1));
                }
                c++;
            }
    } catch (SQLException e){
        JOptionPane.showMessageDialog(view, "Erro de conexão", "Erro", JOptionPane.ERROR_MESSAGE);
    }
        
        
        
        
    
    
}
}
