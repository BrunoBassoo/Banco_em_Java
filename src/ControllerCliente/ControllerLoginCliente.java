package ControllerCliente;

import DB.DB_Cliente;
import DB.conexao_banco;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Cliente;
import views.EntrarCliente;
import views.TelaCliente;

/**
 *
 * @author T-Gamer
 */
public class ControllerLoginCliente {
    private EntrarCliente view;

    public ControllerLoginCliente(EntrarCliente view) {
        this.view = view;
    }
    
    public void loginCliente(){
        String cpf = view.getEntrada_cpf().getText();
        String senha = view.getEntrada_senha().getText();
        Cliente cliente = new Cliente(cpf,senha);
        conexao_banco conexao = new conexao_banco();
        try{
            Connection conn = conexao.getConnection();
            DB_Cliente db = new DB_Cliente(conn);
            ResultSet res = db.consultarCliente(cliente);
            if(res.next()){
                JOptionPane.showMessageDialog(view, "Login Feito", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                TelaCliente tc = new TelaCliente();
                tc.setVisible(true);
                view.setVisible(false);
            } else{
                JOptionPane.showMessageDialog(view, "Login não efetuado", "Erro", JOptionPane.ERROR_MESSAGE);
                
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(view, "Erro de conexão", "Erro", JOptionPane.ERROR_MESSAGE);
                
        }
    }
}
