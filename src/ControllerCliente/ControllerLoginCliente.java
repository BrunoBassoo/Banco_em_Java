package ControllerCliente;

import DB.DB_Cliente;
import DB.conexao_banco;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Cliente;
import views.EntrarCliente;
import views.CadastrarNovoCliente;

/**
 *
 * @author T-Gamer
 */
public class ControllerLoginCliente {
    private EntrarCliente view;

    public ControllerLoginCliente(EntrarCliente view) {
        this.view = view;
    }
    
    public void loginAluno(){
        Cliente cliente = new Cliente(view.getTxtLoginCliente().getText(),view.getTxtSenhaCliente().getText());
        conexao_banco conexao = new conexao_banco();
        try{
            Connection conn = conexao.getConnection();
            DB_Cliente db = new DB_Cliente(conn);
            ResultSet res = db.consultar(cliente);
            if(res.next()){
                JOptionPane.showMessageDialog(view, "Login Feito", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                String CPF = res.getString("cpf");
                String senha = res.getString("senha");
//                CadastrarNovoCliente viewCadastro = new CadastrarNovoCliente(new Cliente( CPF, senha));
//                viewCadastro.setVisible(true);
                view.setVisible(false);
            } else{
                JOptionPane.showMessageDialog(view, "Login não efetuado", "Erro", JOptionPane.ERROR_MESSAGE);
                
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(view, "Erro de conexão", "Erro", JOptionPane.ERROR_MESSAGE);
                
        }
    }
}
