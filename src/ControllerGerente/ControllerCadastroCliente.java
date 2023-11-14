package ControllerGerente;

import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.Cliente;
import DB.conexao_banco;
import views.CadastrarNovoCliente;
import DB.DB_Cliente;
/**
 *
 * @author uniebrunosilva
 */
public class ControllerCadastroCliente {
    private CadastrarNovoCliente view;

    public ControllerCadastroCliente(CadastrarNovoCliente view) {
        this.view = view;
    }
    
    // salvar novo cliente
    public void salvarNovoCliente(){
        
        //pegando os valores que a pessoa colocou nos campos
        String nome = view.getEntrada_nome().getText();
        String senha = view.getEntrada_senha().getText();
        String CPF = view.getEntrada_cpf().getText();
        double saldo = Double.parseDouble(view.getEntrada_saldo().getText());
        
        // pessando os valores para o objeto Cliente
        Cliente cliente = new Cliente(nome,senha,CPF,saldo);
        String cliente_cpf = cliente.getCpf();
        conexao_banco conexao = new conexao_banco();
        try{
            Connection conn = conexao.getConnection();
            DB_Cliente db = new DB_Cliente(conn);
//            for(int cpf = 0; cpf < conexao.length();cpf++){
//                cliente_cpf != db.VerificacaoCliente(cliente);
//            }
            db.inserir(cliente);
            JOptionPane.showMessageDialog(view, "Cliente cadastrado!", "Aviso",JOptionPane.INFORMATION_MESSAGE);
    } catch(SQLException e){
        JOptionPane.showMessageDialog(view, "Erro de conexão, tente novamente!", "Aviso", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
    }
}

