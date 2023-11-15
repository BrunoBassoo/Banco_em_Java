package ControllerCliente;

import DB.DB_Cliente;
import DB.conexao_banco;
import model.Cliente;
import views.CriarConta;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author ZAKAGORILAO
 */
public class ControllerCriarConta {
    private CriarConta view;

    public ControllerCriarConta(CriarConta view) {
        this.view = view;
    }
    
    public void CriarTipoConta(int tipo){
        
        String cpf = view.getEntrada_cpf().getText();
        String senha = view.getEntrada_senha().getText();
        int conta = tipo;
        
        
        Cliente cliente  = new Cliente(cpf,senha,conta);
        conexao_banco conexao = new conexao_banco();
        try{
            Connection conn = conexao.getConnection();
            DB_Cliente db = new DB_Cliente(conn);
            ResultSet res = db.consultarCliente(cliente);
            if(res.next()){
                
            }
            
        } catch(SQLException e){
            e.printStackTrace();
        }
        
    }
}
