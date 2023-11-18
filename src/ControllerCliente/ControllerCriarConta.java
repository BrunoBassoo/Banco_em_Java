package ControllerCliente;

import DB.DB_Cliente;
import DB.conexao_banco;
import model.Cliente;
import views.CriarConta;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;


/**
 *
 * @author ZAKAGORILAO
 */
public class ControllerCriarConta {
    private CriarConta view;

    public ControllerCriarConta(CriarConta view) {
        this.view = view;
    }
    
    public void CriarContaCorrente(){
        
        String cpf = view.getEntrada_cpf().getText();
        String senha = view.getEntrada_senha().getText();
        double valor = Double.parseDouble(view.getEntrada_valor().getText());
        
        Cliente cliente  = new Cliente(cpf,senha);
        conexao_banco conexao = new conexao_banco();
        try{
            Connection conn = conexao.getConnection();
            DB_Cliente db = new DB_Cliente(conn);
            ResultSet res = db.consultarCliente(cliente);
            if(res.next()){
                db.criarContaCorrente(cliente,valor);
                JOptionPane.showMessageDialog(null, "Conta Corrente criada com sucesso!", "Aviso", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch(SQLException e){
            JOptionPane.showMessageDialog(null, "Erro de conexão!", "Aviso", JOptionPane.ERROR_MESSAGE);
            
            e.printStackTrace();
        }
        
    }
}
