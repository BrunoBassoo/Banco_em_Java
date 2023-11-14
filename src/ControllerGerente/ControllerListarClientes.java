package ControllerGerente;

import DB.conexao_banco;
import views.TelaGerente;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author T-Gamer
 */
public class ControllerListarClientes {
    private TelaGerente view;

    public ControllerListarClientes(TelaGerente view) {
        this.view = view;
    }
    
    conexao_banco conexao = new conexao_banco();
//    try{
//        Connection conn = conexao.getConnection();
//        DB_Cliente db = new DB_Cliente(conn);
//    } catch (SQLException e){
//    JOptionPane.showMessageDialog(view, "Erro de conexao!", "Alerta", JOptionPane.ERROR_MESSAGE);
//}
}
