package ControllerGerente;

import DB.DB_Cliente;
import DB.conexao_banco;
import views.ExcluirCliente;
import model.Cliente;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
/**
 *
 * @author uniebrunosilva
 */
public class ControllerExcluirCliente {
    private ExcluirCliente view;

    public ControllerExcluirCliente(ExcluirCliente view) {
        this.view = view;
    }
    
    // excluir cliente
    
    
    
    
    public void Excluir(){

        //pegando o valore que a pessoa colocou no campo
        String CPF = view.getTxtCpf().getText();        
        // construtor da heranca do Cliente com Pessoa
        Cliente cliente = new Cliente(CPF);
        
        // estabelecendo conexao com BD
        conexao_banco conexao = new conexao_banco();
        try{
            Connection conn = conexao.getConnection();
            DB_Cliente db = new DB_Cliente(conn);
            db.remover(cliente);
            JOptionPane.showMessageDialog(view,"Cliente removido!","alerta",JOptionPane.INFORMATION_MESSAGE );
        } catch( SQLException e){
            JOptionPane.showMessageDialog(view, "Erro de conexão, tente novamente!", "Aviso", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
        }

    }
}
